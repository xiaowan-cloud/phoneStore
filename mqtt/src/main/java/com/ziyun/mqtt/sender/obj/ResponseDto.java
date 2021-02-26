package com.ziyun.mqtt.sender.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    String clientid;
    String devtemplateid;
    String secret;
    String deviceid;
    int no_repeat_count;
    String data_version;
    String pboxid;
    String userName;
    String password;
    String mqttHost;
    String data;
    Payload payload;
    int type;
}
