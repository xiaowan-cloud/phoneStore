package com.ziyun.mqtt.sender.utile;

/**
 * Created by wxu2 on 2017/6/12.
 */
public enum Code {

    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 文件上传失败
     */
    FILE_UPLOAD_FAILED(416),
    /**
     * 执行失败
     */
    FAILED(417),
    /**
     * 票凭证验证失败
     */
    TICKET_FAILED(418),
    /**
     * 短信发送失败
     */
    MESSAGE_FAILED(419),
    /**
     * 参数验证失败
     */
    PARAM_VALIDATE_FAILED(400),
    /**
     * 权限验证失败
     */
    AUTHENTICATION_FAILED(401),
    /**
     *密码失败
     */
    PASSWORD_FAILED(402),
    /**
     * 非法客户端
     */
    INVALID_CLIENT(403),
    
    /**
     * 找不到资源
     */
    RESOURCE_NOT_FOUND(404),
    /**
     * 请求方法不允许
     */
    METHOD_NOT_SUPPORTED(405),
    /**
     * 执行授权失败
     */
    AUTHORITY_FAILED(406),
    /**
     * 超级管理员
     */
     FILE_SUPER_ADMIN(407),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(500);

    private Integer value;

    Code(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

}
