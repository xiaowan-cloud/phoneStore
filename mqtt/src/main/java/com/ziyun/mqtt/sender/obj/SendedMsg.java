package com.ziyun.mqtt.sender.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendedMsg {
    short magic_id = 0x5151;
    short msg_type = 1;
    short payload_len = 28;
    Payload payload = null;
}
