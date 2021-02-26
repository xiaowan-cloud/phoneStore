package com.ziyun.mqtt.sender.utile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2013099994778186749L;

    private Integer errorCode;
    private String subErrorCode;
    private String errorMessage;

    @Override
    public String getMessage() {
        return errorMessage;
    }

    public BaseException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(Integer errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public BaseException(String subErrorCode, String errorMessage) {
        super(errorMessage);
        this.subErrorCode = subErrorCode;
        this.errorMessage = errorMessage;
    }

    //idm调用返回错误
    public BaseException(JSONObject jsonObject) {
        super(jsonObject.toString());
        this.errorCode = (Integer) jsonObject.get("code");
        this.errorMessage = (String) jsonObject.get("message");
        this.subErrorCode = (String) jsonObject.get("subCode");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
