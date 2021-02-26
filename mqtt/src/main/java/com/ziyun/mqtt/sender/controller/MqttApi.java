package com.ziyun.mqtt.sender.controller;

import com.ziyun.mqtt.sender.obj.ResponseDto;
import com.ziyun.mqtt.sender.runner.Runner;
import com.ziyun.mqtt.sender.utile.ResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author：梁亚泽
 * @title：默认描述
 * @date：17:15 2020/10/29
 * @修改人：
 * @修改时间：17:15 2020/10/29
 * @修改描述：默认描述
 */
@Log4j2
@RestController
@RequestMapping("/mqtt")
@Api(value = "mqtt", tags = "发送mqtt数据")
public class MqttApi {
    @Autowired
    private Runner runner;

    @ApiOperation("发送mqtt数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientid", value = "客户端ID", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "devtemplateid", value = "模板ID", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "secret", value = "secret", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "deviceid", value = "设备ID", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "data_version", value = "数据版本", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "electric_current", value = "电流", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "voltage", value = "电压", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "longitude", value = "经度", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "latitude", value = "纬度", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "is_apart", value = "拆除预警", dataType = "char", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "signal", value = "信号", dataType = "byte", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "collection_unixtime", value = "采集时间", dataType = "int", required = true, paramType = "Body")
    })
    @PostMapping("/send/one")
    public ResponseDTO<?> moveCatalog(@ApiParam @RequestBody @Valid ResponseDto responseDto) {
        runner.runOne(responseDto);
        return new ResponseDTO<>(200,"发送成功");
    }

    @ApiOperation("发送mqtt数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientid", value = "客户端ID", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "devtemplateid", value = "模板ID", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "secret", value = "秘钥", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "deviceid", value = "设备ID", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "data_version", value = "数据版本", dataType = "String", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "electric_current", value = "电流", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "voltage", value = "电压", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "longitude", value = "经度", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "latitude", value = "纬度", dataType = "float", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "is_apart", value = "拆除预警", dataType = "char", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "signal", value = "信号", dataType = "byte", required = true, paramType = "Body"),
            @ApiImplicitParam(name = "collection_unixtime", value = "采集时间", dataType = "int", required = true, paramType = "Body")
    })
    @PostMapping("/send/eight")
    public ResponseDTO<?> moveCatalogTwo(@ApiParam @RequestBody @Valid ResponseDto responseDto) {
        runner.runEight(responseDto);
        return new ResponseDTO<>(200,"发送成功");
    }

}
