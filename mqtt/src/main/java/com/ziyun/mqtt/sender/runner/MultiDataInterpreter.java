package com.ziyun.mqtt.sender.runner;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 将多发送的数据解析成一条一条的数据，然后按照顺序返回数据<br>
 * <b>判断是否是可设置数据的依据是data_version字段的值大于等于4.0.2，并且data的内容可以base64解码</b></br>
 * @author jundaixie
 *
 */
public class MultiDataInterpreter {
	final Base64.Decoder decoder = Base64.getDecoder();
    private static int DATA_PER_SIZE = 28; 
    private Logger logger = LoggerFactory.getLogger(MultiDataInterpreter.class.getName());
    
    public MultiDataInterpreter(){}
    
    public float getFloatByBytes(byte b1, byte b2, byte b3, byte b4) {
    	ByteBuffer buf = ByteBuffer.allocateDirect(4); 
    	buf.put(0, b1);
    	buf.put(1, b2);
    	buf.put(2, b3);
    	buf.put(3, b4);
    	buf.rewind();
    	float f2=buf.getFloat();
    	return f2;
    }
    
    public int getIntFromBytes(byte high_h, byte high_l, byte low_h, byte low_l) {
        return (high_h & 0xff) << 24 | (high_l & 0xff) << 16 | (low_h & 0xff) << 8 | low_l & 0xff;
    }
    
    public int getIntFromBytes(byte low_h, byte low_l) {
    	int lowh = 0x00000000 | low_h;
    	int lowl = 0x00000000 | low_l;
    	return (lowh << 8 ) | lowl;
    }
    
    private String convertTimestampToStr(long tmsp) {
    	long tms = tmsp * 1000L - 8 * 3600000L;;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(new Date(tms));
    }
    
    
    public List<JSONObject> explainMultiData(com.alibaba.fastjson.JSONObject jobj) {
    	if(null == jobj) {
			return null;
		}
    	if(!jobj.containsKey("data_version") || !jobj.containsKey("data")) {
    		logger.info("There is NO version or data field."); 
    		return null;
    	}
    	byte[] dataBytes = decoder.decode(jobj.getString("data"));
    	if(null == dataBytes || dataBytes.length <= 0) {
    		logger.info("Data is empty.");
    		return null;
    	}
    	logger.info("byte's length = " + dataBytes.length);
    	int dateNumber = 0;
    	List<JSONObject> datas = new ArrayList<>();
    	
    	while((dateNumber + 1) * DATA_PER_SIZE <= dataBytes.length) {
    		JSONObject _obj = JSONObject.parseObject("{}"); 
    		_obj.put("data_version", jobj.getString("data_version"));
    		if(jobj.containsKey("clientid")) {
    		    _obj.put("clientid", jobj.getString("clientid"));
    		}
    		if(jobj.containsKey("secret")) {
    			_obj.put("secret", jobj.getString("secret"));
    		}
    		if(jobj.containsKey("devtemplateid")) {
    			_obj.put("devtemplateid", jobj.getString("devtemplateid"));
    		}
    		if(jobj.containsKey("deviceid")) {
    			_obj.put("deviceid", jobj.getString("deviceid"));
    		}
    		JSONObject _data = JSONObject.parseObject("{}"); 
    		char c1 = (char) dataBytes[dateNumber * DATA_PER_SIZE + 0];
    		char c2 = (char) dataBytes[dateNumber * DATA_PER_SIZE + 1];
    		_data.put("magic_id", ("" + c1) + c2);
    		_data.put("msg_type", "" + getIntFromBytes(dataBytes[dateNumber * DATA_PER_SIZE + 3], dataBytes[dateNumber * DATA_PER_SIZE + 2]));
    		_data.put("payload_len", "" + getIntFromBytes(dataBytes[dateNumber * DATA_PER_SIZE + 5], dataBytes[dateNumber * DATA_PER_SIZE + 4]));
    		_data.put("electric_current", "" + getFloatByBytes(dataBytes[dateNumber * DATA_PER_SIZE + 9], dataBytes[dateNumber * DATA_PER_SIZE + 8], dataBytes[dateNumber * DATA_PER_SIZE + 7], dataBytes[dateNumber * DATA_PER_SIZE + 6]));
    		_data.put("voltage", "" + getFloatByBytes(dataBytes[dateNumber * DATA_PER_SIZE + 13], dataBytes[dateNumber * DATA_PER_SIZE + 12], dataBytes[dateNumber * DATA_PER_SIZE + 11], dataBytes[dateNumber * DATA_PER_SIZE + 10]));
    		_data.put("longitude", "" + getFloatByBytes(dataBytes[dateNumber * DATA_PER_SIZE + 17], dataBytes[dateNumber * DATA_PER_SIZE + 16], dataBytes[dateNumber * DATA_PER_SIZE + 15], dataBytes[dateNumber * DATA_PER_SIZE + 14]));
    		_data.put("latitude", "" + getFloatByBytes(dataBytes[dateNumber * DATA_PER_SIZE + 21], dataBytes[dateNumber * DATA_PER_SIZE + 20], dataBytes[dateNumber * DATA_PER_SIZE + 19], dataBytes[dateNumber * DATA_PER_SIZE + 18]));
    		_data.put("is_apart", "" + dataBytes[dateNumber * DATA_PER_SIZE + 22]);
    		_data.put("signal", "" + dataBytes[dateNumber * DATA_PER_SIZE + 23]);
    		_data.put("timestamp", "" + getIntFromBytes(dataBytes[dateNumber * DATA_PER_SIZE + 27], dataBytes[dateNumber * DATA_PER_SIZE + 26], dataBytes[dateNumber * DATA_PER_SIZE + 25], dataBytes[dateNumber * DATA_PER_SIZE + 24]));
    		_data.put("data_col_time", convertTimestampToStr(getIntFromBytes(dataBytes[dateNumber * DATA_PER_SIZE + 27], dataBytes[dateNumber * DATA_PER_SIZE + 26], dataBytes[dateNumber * DATA_PER_SIZE + 25], dataBytes[dateNumber * DATA_PER_SIZE + 24])));

    		System.out.println(dataBytes[dateNumber * DATA_PER_SIZE + 24]);
    		System.out.println(dataBytes[dateNumber * DATA_PER_SIZE + 25]);
    		System.out.println(dataBytes[dateNumber * DATA_PER_SIZE + 26]);
    		System.out.println(dataBytes[dateNumber * DATA_PER_SIZE + 27]);

    		System.out.println(getIntFromBytes(dataBytes[dateNumber * DATA_PER_SIZE + 27], dataBytes[dateNumber * DATA_PER_SIZE + 26], dataBytes[dateNumber * DATA_PER_SIZE + 25], dataBytes[dateNumber * DATA_PER_SIZE + 24]));
    		_obj.put("data", _data);
    		datas.add(_obj);
    		dateNumber ++;
    	}
    	sortByTime(datas);
    	return datas;
    }

	private void sortByTime(List<JSONObject> datas) {
		if(null == datas || datas.size() <= 0) { return; }
		for(int i=0; i<datas.size() - 1; i++) {
			for(int j = i+1; j<datas.size(); j++) {
				JSONObject jo1 = datas.get(i);
				JSONObject jo2 = datas.get(j);
				if(null != jo1 && null != jo2 && jo1.containsKey("data") && jo2.containsKey("data")) {
					JSONObject subjo1 = jo1.getJSONObject("data");
					JSONObject subjo2 = jo2.getJSONObject("data");
					if(subjo1.containsKey("timestamp") && subjo2.containsKey("timestamp")) {
						String tm1 = subjo1.getString("timestamp");
						String tm2 = subjo2.getString("timestamp");
						if(null != tm1 && null != tm2 && tm1.compareTo(tm2) > 0) {
							JSONObject jo3 = jo1;
							datas.set(i, jo2);
							datas.set(j, jo3);
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String str = "{\"clientid\":\"ff7a4ddfb2c54a7b85c58943e17e80e4\",\"data\":\"UVEAAQAYGQQGQLKdzz/OKvNCPQr6QQE2XAn6XQ==\",\"data_version\":\"4.0.2\",\"deviceid\":\"6\",\"devtemplateid\":\"7LkeY63752G5c9U05HFL\",\"no_repeat_count\":4039,\"secret\":\"5cea06181n\"} ";
		JSONObject jo = JSONObject.parseObject(str);
		
		MultiDataInterpreter m = new MultiDataInterpreter();
		List<JSONObject> obs = m.explainMultiData(jo);
		System.out.println("JSON:"+obs);
		for(int i=0; i < obs.size(); i++) {
			System.out.println(obs.get(i).getJSONObject("data").getString("collection_time"));
			System.out.println(obs.get(i).getJSONObject("data").getString("electric_current"));
			System.out.println(obs.get(i).getJSONObject("data").getString("voltage"));
			System.out.println(obs.get(i).getJSONObject("data").getString("longitude"));
			System.out.println(obs.get(i).getJSONObject("data").getString("latitude"));
			System.out.println(obs.get(i).getJSONObject("data").getString("is_apart"));
			System.out.println(obs.get(i).getJSONObject("data").getString("signal"));
		}
	}
}
