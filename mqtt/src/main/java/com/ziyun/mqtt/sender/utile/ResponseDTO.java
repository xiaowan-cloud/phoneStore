package com.ziyun.mqtt.sender.utile;


import lombok.Data;

@Data
public class ResponseDTO<T> {

    private Integer code = Code.SUCCESS.value();
    private String message = Message.SUCCESS.value();
    private String subCode;
    private T data;
    
    public ResponseDTO() {
    }
    
    public ResponseDTO(Integer code, String message, String subCode, T data) {
    	this.code = code;
    	this.message = message;
    	this.subCode = subCode;
    	this.data = data;
    }

    public ResponseDTO(T data) {
        this.data = data;
    }

    public ResponseDTO(BaseException exception) {
        this.code = exception.getErrorCode();
        this.message = exception.getMessage();
        this.subCode = exception.getSubErrorCode();
    }

    public ResponseDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
