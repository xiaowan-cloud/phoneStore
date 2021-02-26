package com.ziyun.mqtt.sender.runner;

import com.alibaba.fastjson.JSON;
import com.ziyun.mqtt.sender.obj.Payload;
import com.ziyun.mqtt.sender.obj.ResponseDto;
import com.ziyun.mqtt.sender.utile.Aes256Utils;
import com.ziyun.mqtt.sender.utile.BaseException;
import com.ziyun.mqtt.sender.utile.Sha256;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Log4j2
@Component
public class Runner {
	
	private static String mqttHost;
	private static String userName;
	private static String password;
	private static String mqttTopic;
	private static String clientId;
	Random rdm = new Random(System.currentTimeMillis());
	private static final Integer qos=1;
	public static final String KEY="104eaf6fc700dba2d16f4bca71f8bb57";

	public void runOne(ResponseDto responseDto) {
		addInfo(responseDto);
	}

	public void runEight(ResponseDto responseDto) {
		for(int i=0;i<8;i++){
			int cnt=i;
			log.info(" -- Collected once: " + (++cnt));
			addInfo(responseDto);
		}
	}

	private void addInfo(ResponseDto responseDto) {
		mqttTopic(responseDto);
		List<Byte> contents = new ArrayList<>();
		addIntoList(contents, convertToBase64(responseDto.getPayload()));
		ResponseDto rsp = new ResponseDto();
		rsp.setClientid(responseDto.getClientid());
		rsp.setDevtemplateid(responseDto.getDevtemplateid());
		rsp.setSecret(responseDto.getSecret());
		rsp.setDeviceid(responseDto.getDeviceid());
		rsp.setData_version(responseDto.getData_version());
		rsp.setNo_repeat_count(rdm.nextInt(10000));
		rsp.setData(responseDto.getType()==0?Base64.getEncoder().encodeToString(convertToArray(contents)):new String(Base64Utils.encode(Aes256Utils.encrypt(convertToArray(contents), (responseDto.getPboxid() + KEY).substring(0, 32)))));
		rsp.setPboxid(new Sha256().SHA256(responseDto.getPboxid() + KEY));
		String jsonString = JSON.toJSONString(rsp);
		log.info(jsonString);
		sendMsg(Runner.qos, jsonString);
		contents.clear();
	}

	private void mqttTopic(ResponseDto responseDto) {
		mqttTopic=responseDto.getClientid()+"/"+responseDto.getDevtemplateid();
		clientId=responseDto.getClientid();
		mqttHost=responseDto.getMqttHost();
		userName=responseDto.getUserName();
		password=responseDto.getPassword();

		responseDto.getPayload().setData_col_time(Math.toIntExact(System.currentTimeMillis() / 1000 + 28800));
		//判断如果不传时间，就是系统当前时间
		String acquisitionTime = responseDto.getPayload().getAcquisitionTime();
		responseDto.getPayload().setData_col_time(StringUtils.isNotBlank(acquisitionTime)?dateToStamp(acquisitionTime):dateToStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()))));
	}
	
	/**
	 * 时间转换时间戳
	 * @param s 时间
	 * @return : long 毫秒
	 * @author : 梁亚泽
	 * @date : 2020/12/23 11:44
	 */
	public long dateToStamp(String s){
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s).getTime();
		}catch (Exception e){
			log.error(e.getMessage(),e);
			throw new BaseException(400,"时间转换失败");
		}
	}

	private byte[] convertToArray(List<Byte> contents) {
		byte[] bs = new byte[contents.size()];
		for(int i=0; i<contents.size(); i++) {
		    bs[i] = contents.get(i);
		}
		return bs;
	}

	private void addIntoList(List<Byte> contents, byte[] c6) {
		for (byte b : c6) {
			contents.add(b);
		}
	}

	private void sendMsg(int qos, String msg) {
		try{
			MemoryPersistence persistence = new MemoryPersistence();
			// 创建客户端
			MqttClient sampleClient = new MqttClient(mqttHost, clientId, persistence);
			// 创建链接参数
			MqttConnectOptions connOpts = new MqttConnectOptions();
			// 在重新启动和重新连接时记住状态
			connOpts.setCleanSession(false);
			// 设置连接的用户名
			connOpts.setUserName(userName);
			connOpts.setPassword(password.toCharArray());
			// 建立连接
			sampleClient.connect(connOpts);
			sampleClient.setCallback(new PushCallback());
			// 创建消息
			MqttMessage message = new MqttMessage(msg.getBytes());
			// 设置消息的服务质量
			message.setQos(qos);
			// 发布消息
			sampleClient.publish(mqttTopic, message);
			// 断开连接
			sampleClient.disconnect();
			// 关闭客户端
			sampleClient.close();
			log.info("发送成功");
		}catch(Exception e) {
			log.error(e);
		}
	}

	public static byte[] float2byte(float f) {
		int fbit = Float.floatToIntBits(f);
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (fbit >> (24 - i * 8));
		}
		int len = b.length;
		byte[] dest = new byte[len];
		System.arraycopy(b, 0, dest, 0, len);
		byte temp;
		for (int i = 0; i < len / 2; ++i) {
			temp = dest[i];
			dest[i] = dest[len - i - 1];
			dest[len - i - 1] = temp;
		}
		return dest;
	}
	
	public static byte[] int2Bytes(int num) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte)(num >>> 24);
		bytes[1] = (byte)(num >>> 16);
		bytes[2] = (byte)(num >>> 8);
		bytes[3] = (byte)num;
		return bytes;
	}
	public static byte[] int2Bytes(long num) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte)(num >>> 24);
		bytes[1] = (byte)(num >>> 16);
		bytes[2] = (byte)(num >>> 8);
		bytes[3] = (byte)num;
		return bytes;
	}

	private byte[] convertToBase64(Payload payload) {
//		Payload payload = new Payload();
//		payload.setCollection_unixtime((int) ((System.currentTimeMillis() + 3600000 * 8) / 1000));
//		System.out.println("collection_time = " + payload.getCollection_unixtime());
//		payload.setElectric_current((float) (rdm.nextInt(3000) * 0.001) );
//		payload.setIs_apart((char) (rdm.nextInt(11) / 10));
//		payload.setLatitude(31.255f);
//		payload.setLongitude(121.5836f);
//		payload.setSignal((byte) rdm.nextInt(100));
//		payload.setVoltage((float) (rdm.nextInt(10000) * 0.001));

		byte[] content = new byte[28];
		content[0] = 0x51;
		content[1] = 0x51;
		content[2] = 0x00;
		content[3] = 0x01;
		content[4] = 0x00;
		content[5] = 0x18;
		byte[] elec = float2byte(payload.getElectric_current());
		content[6] = elec[0];
		content[7] = elec[1];
		content[8] = elec[2];
		content[9] = elec[3];
		byte[] volt = float2byte(payload.getVoltage());
		content[10] = volt[0];
		content[11] = volt[1];
		content[12] = volt[2];
		content[13] = volt[3];
		byte[] logi = float2byte(payload.getLongitude());
		content[14] = logi[0];
		content[15] = logi[1];
		content[16] = logi[2];
		content[17] = logi[3];
		byte[] lati = float2byte(payload.getLatitude());
		content[18] = lati[0];
		content[19] = lati[1];
		content[20] = lati[2];
		content[21] = lati[3];
		content[22] = (byte) payload.getIs_apart();
		content[23] = payload.getSignal();
		byte[] tm = int2Bytes(payload.getData_col_time() / 1000 + 28800);
		content[24] = tm[3];
		content[25] = tm[2];
		content[26] = tm[1];
		content[27] = tm[0];
		return content;
	}
	
	public static int getIntFromBytes(byte high_h, byte high_l, byte low_h, byte low_l) {
        return (high_h & 0xff) << 24 | (high_l & 0xff) << 16 | (low_h & 0xff) << 8 | low_l & 0xff;
    }
	
	public static void main(String[] args) {
		Runner r = new Runner();
		byte[] datas = r.convertToBase64(new Payload());
		System.out.println("datas[24]" + datas[24]);
		System.out.println("datas[25]" + datas[25]);
		System.out.println("datas[26]" + datas[26]);
		System.out.println("datas[27]" + datas[27]);
		System.out.println(getIntFromBytes(datas[27], datas[26], datas[25], datas[24]));
		System.out.println(Base64.getEncoder().encodeToString(datas));

	}
}
