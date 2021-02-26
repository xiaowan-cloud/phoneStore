package com.ziyun.mqtt.sender.obj;

import com.ziyun.mqtt.sender.utile.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class Payload {
    float electric_current;
    float voltage;
    float longitude;
    float latitude;
    float is_apart;
    byte signal;
    long data_col_time;
    byte cncStatus;
    String acquisitionTime;
}
