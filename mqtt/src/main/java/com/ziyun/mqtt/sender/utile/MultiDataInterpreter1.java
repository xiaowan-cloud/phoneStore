package com.ziyun.mqtt.sender.utile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class MultiDataInterpreter1 {
    private Logger logger = LoggerFactory.getLogger(MultiDataInterpreter1.class.getName());
    private static int DEFAULT_DATA_PER_SIZE = 28;
    private static int DEFAULT_DATA_PAYLOAD_LEN = DEFAULT_DATA_PER_SIZE - 6;
    private static int HAVE_POWER_DATA_PER_SIZE = 29;
    private static int HAVE_POWER_DATA_PAYLOAD_LEN = HAVE_POWER_DATA_PER_SIZE - 6;
    private static int DATA_PER_SIZE = 28;
    final Base64.Decoder decoder = Base64.getDecoder();

    public MultiDataInterpreter1(){}

    private static final long ONE_HOUR_TIMESTAMP = 1000 * 60 * 60;
    private static final long ONE_DAY_TIMESTAMP = ONE_HOUR_TIMESTAMP * 24;

    public int getIntFromBytes(byte high_h, byte high_l, byte low_h, byte low_l) {
        return (high_h & 0xff) << 24 | (high_l & 0xff) << 16 | (low_h & 0xff) << 8 | low_l & 0xff;
    }

    public int getIntFromBytes(byte low_h, byte low_l) {
        int lowh = 0x00000000 | low_h;
        int lowl = 0x00000000 | low_l;
        return (lowh << 8 ) | lowl;
    }
    public byte getByteFromInt(int i, int byteIndex){
        return Integer.valueOf((i >> byteIndex) & 0xff).byteValue();
    }

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
    public byte[] parse(byte[] decodeBytes){
        int dataNumber = 0;
        long yesterdayTimestamp = getYesterdayTimestamp();
        long yesterdayOneAMTimestamp = yesterdayTimestamp + 1000 * 60 * 60;
        while((dataNumber + 1) * DATA_PER_SIZE <= decodeBytes.length) {
            long timestamp = getIntFromBytes(decodeBytes[dataNumber * DATA_PER_SIZE + 27],
                    decodeBytes[dataNumber * DATA_PER_SIZE + 26],
                    decodeBytes[dataNumber * DATA_PER_SIZE + 25],
                    decodeBytes[dataNumber * DATA_PER_SIZE + 24]) * 1000L - 8 * ONE_HOUR_TIMESTAMP;
            if(yesterdayTimestamp < timestamp && timestamp < yesterdayOneAMTimestamp){
                timestamp += 8 * ONE_HOUR_TIMESTAMP;
                int trueValue = (int)((timestamp + ONE_DAY_TIMESTAMP) / 1000);
                byte byteFromIntIndex0 = getByteFromInt(trueValue, 0);
                byte byteFromIntIndex8 = getByteFromInt(trueValue, 8);
                byte byteFromIntIndex16 = getByteFromInt(trueValue, 16);
                byte byteFromIntIndex24 = getByteFromInt(trueValue, 24);

                decodeBytes[dataNumber * DATA_PER_SIZE + 27] = byteFromIntIndex24;
                decodeBytes[dataNumber * DATA_PER_SIZE + 26] = byteFromIntIndex16;
                decodeBytes[dataNumber * DATA_PER_SIZE + 25] = byteFromIntIndex8;
                decodeBytes[dataNumber * DATA_PER_SIZE + 24] = byteFromIntIndex0;
            }
            dataNumber ++;
        }
        return decodeBytes;
    }
    private long getYesterdayTimestamp(){
        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.add(Calendar.DAY_OF_MONTH, -1);
        calendarInstance.set(Calendar.HOUR_OF_DAY, 0);
        calendarInstance.set(Calendar.MINUTE, 0);
        calendarInstance.set(Calendar.SECOND, 0);
        calendarInstance.set(Calendar.MILLISECOND, 0);

        return calendarInstance.getTimeInMillis();
    }

    public JSONArray explainMultiData(String originalMessage) {
        byte[] decodeBytes = decoder.decode(originalMessage);
        JSONArray jsonArray = analysisOriginalMessage(decodeBytes);
        logger.info("解析出的数据为：" + jsonArray);
        return jsonArray;
    }

    private JSONArray analysisOriginalMessage(byte[] dataBytes){
        int dataPerSize = DEFAULT_DATA_PER_SIZE;
        int dateNumber = 0;
        JSONArray datas = new JSONArray();
//        获取 payloadLen 判断如何解析
        int payloadLen = getIntFromBytes(dataBytes[5], dataBytes[4]);
        if (payloadLen == DEFAULT_DATA_PAYLOAD_LEN) {
            dataPerSize = DEFAULT_DATA_PER_SIZE;
        }
        if (payloadLen == HAVE_POWER_DATA_PAYLOAD_LEN) {
            dataPerSize = HAVE_POWER_DATA_PER_SIZE;
        }
        while ((dateNumber + 1) * dataPerSize <= dataBytes.length) {
            datas.add(getData(dataBytes, dateNumber, dataPerSize));
            dateNumber++;
        }
        return datas;
    }

    private JSONObject getData(byte[] dataBytes, int dateNumber, Integer dataPerSize){
        JSONObject data = JSONObject.parseObject("{}");
        char c1 = (char) dataBytes[dateNumber * dataPerSize + 0];
        char c2 = (char) dataBytes[dateNumber * dataPerSize + 1];
        data.put("magic_id", ("" + c1) + c2);
        data.put("msg_type", "" + getIntFromBytes(dataBytes[dateNumber * dataPerSize + 3], dataBytes[dateNumber * dataPerSize + 2]));
        data.put("payload_len", "" + getIntFromBytes(dataBytes[dateNumber * dataPerSize + 5], dataBytes[dateNumber * dataPerSize + 4]));
        data.put("electric_current", "" + getFloatByBytes(dataBytes[dateNumber * dataPerSize + 9], dataBytes[dateNumber * dataPerSize + 8], dataBytes[dateNumber * dataPerSize + 7], dataBytes[dateNumber * dataPerSize + 6]));
        data.put("voltage", "" + getFloatByBytes(dataBytes[dateNumber * dataPerSize + 13], dataBytes[dateNumber * dataPerSize + 12], dataBytes[dateNumber * dataPerSize + 11], dataBytes[dateNumber * dataPerSize + 10]));
        data.put("longitude", "" + getFloatByBytes(dataBytes[dateNumber * dataPerSize + 17], dataBytes[dateNumber * dataPerSize + 16], dataBytes[dateNumber * dataPerSize + 15], dataBytes[dateNumber * dataPerSize + 14]));
        data.put("latitude", "" + getFloatByBytes(dataBytes[dateNumber * dataPerSize + 21], dataBytes[dateNumber * dataPerSize + 20], dataBytes[dateNumber * dataPerSize + 19], dataBytes[dateNumber * dataPerSize + 18]));
        data.put("is_apart", "" + dataBytes[dateNumber * dataPerSize + 22]);
        data.put("signal", "" + dataBytes[dateNumber * dataPerSize + 23]);
        int timestamp = getIntFromBytes(dataBytes[dateNumber * dataPerSize + 27], dataBytes[dateNumber * dataPerSize + 26], dataBytes[dateNumber * dataPerSize + 25], dataBytes[dateNumber * dataPerSize + 24]);
        data.put("timestamp", "" + timestamp);
        if(dataPerSize == HAVE_POWER_DATA_PER_SIZE){
            data.put("power_flag", "" + dataBytes[dateNumber * dataPerSize + 28]);
        }
        data.put("date_col_time", convertTimestamp(timestamp));
        return data;
    }

    private String convertTimestampToStr(long tmsp) {
        long tms = tmsp * 1000L - 8 * 3600000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(tms));
    }

    private String convertTimestamp(long tmsp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(tmsp * 1000L));
    }
}