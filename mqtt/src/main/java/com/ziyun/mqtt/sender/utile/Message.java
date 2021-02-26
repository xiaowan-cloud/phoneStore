package com.ziyun.mqtt.sender.utile;

/**
 * Created by wxu2 on 2017/6/12.
 */
public enum Message {

    /**
     * 成功
     */
    SUCCESS("Success"),

    /**
     * 请求失败
     */
    FAILED("Request failed"),

    /**
     * 权限验证失败
     */
    AUTH_FAILED("权限验证失败"),

    AUTHENTICATION_FAILED("身份认证失败"),
    
    AUTHENTICATION_DEAL_DATA_FAILED("无权限操作该数据！"),

    DOMAIN_ACCOUNT_IS_NOT_SUPPER("不支持域账号"),

    NON_DOMAIN_ACCOUNT_IS_NOT_SUPPER("不支非持域账号"),

    BUSINESS_DATA_NOT_FOUND("业务数据未找到"),

    SYSTEM_DATA_NOT_FOUND("系统数据未找到"),

    REPEATED_PARAMETER("参数重复"),

    BUSINESS_LOGIN_ERROR("业务逻辑错误"),

    REPEAT_OPERATION("重复操作"),

    EXECUTION_ORDER_ERROR("执行顺序错误"),

    ASSOCIATION_CONSTRAIN("数据关联约束错误"),

    PARAMETER_VERIFY_FAILED("参数校验失败"),

    HIERARCHICAL_AUTH_FAILED("层级权限错误"),

    FUNCTION_AUTH_FAILED("功能权限验证失败"),

    MENU_AUTH_FAILED("菜单权限验证失败"),

    THIRD_SERVER_ERROR("第三方服务错误"),

    INTERNET_CONNECTION_ERROR("网络连接错误"),

    SERVER_CALL_FAILED("服务调用失败"),

    DATA_STATUS_ERROR("数据状态错误"),

    FILE_TYPE_ERROR("文件类型错误"),

    MAX_UPLOAD_SIZE_EXCEEDED_ERROR("上传文件超出最大大小错误"),

    DAMAGED_FILE("文件损坏"),

    FILE_CONTENT_ERROR("文件内容错误"),

    FILE_NOT_FOUND("文件不存在"),

    FILE_CONTENT_EMPTY("文件内容为空"),

    FILE_TRANSFORM_ERROR("数据转换错误"),

    /**
     * 授权失败
     */
    AUTHORIZE_FAILED("Authorize failed"),
    
    /**
     * 系统异常错误
     */
    SYSTEM_ERROR("Internal server error"),
    /**
     * 参数缺失
     */
    PARAM_MISSING("必填参数缺失"),
    /**
     * 参数验证失败
     */
    PARAMS_VALIDATION_FAILED("Parameter validation failed"),

    PARAMETER_VALIDATION("参数验证失败"),
    
    /**
     * 参数验证失败
     */
    PARAMS_NAME_ERROR("Parameter name error"),
    
    /**
     * 参数已存在
     */
    PARAM_EXISTED("Parameter already exists"),
    /**
     * 无效的客户端
     */
    CLIENT_INVALID("Invalid App"),
    /**
     * 无效请求
     */
    REQUEST_INVALID("Invalid request"),
    /**
     *
     */
    VALID("valid"),
    /**
     * clientId验证失败
     */
    CLIENT_ID_FAILED("客户端id验证失败！"),
    
    /**
     * clientId验证失败
     */
    CLIENT_AUTH_FAILED("客户端权限验证失败！"),
    /**
     * clientSecret验证失败
     */
    CLIENT_SECRET_FAILED("客户端密码验证失败！"),
    /**
     * 资源未找到
     */
    RESOURCE_NOT_FOUND("Resource not found"),
    /**
     * 资源未找到
     */
    INVALID_ID_OR_PASSWORD("账号和密码不匹配！"),
    /**
     * 查询出的层级为空
     */
    HIERARCHY_EMPTY("hierarchy is empty"),
    
    /**
     * 验证码发送频率过高
     */
    SECURITY_CODE_FREQUENCY_LIMIT("security code frequency limit"),
    
    /**
     * 短信发送失败
     */
    SHORT_MESSAGE_SEND_FAILED("short meesage send failed"),
    
    /**
     * 参数被占用
     */
    PARAM_DUPLICATED("param can not be duplicated with the other users"),

    /**
     * 未找到指定设备
     */
    DEVICE_NOT_FOUND("device not found"),

    /**
     * 未找到指定设备
     */
    DATA_NOT_FOUND("data not found"),

    /**
     * 未找到模板
     */
    TEMPLATE_NOT_FOUND("template not found"),
    
    /**
     * 层级code不存在
     */
    HIERARCHY_NOT_FOUND("层级不存在！"),
    
    /**
     * ssoId不存在
     */
    SSO_NOT_FOUND("用户不存在！"),
    
    /**
     * ssoId无层级
     */
    SSO_NO_HIERARCHY("该用户找不到层级！"),
    /**
     * 操作资源已发生改动
     */
    FAILED_CHANGED("此项已更改"),

    /**
     * iot设备模板为空
     */
    IOT_TEMPLATE_IS_EMPTY("iot template is empty"),

    START_TIME_GREATER_THAN_END_TIME("start time greater than end time"),
    /**
     * 未找到hCode
     */
    HCODE_NOT_FOUND("hCode not found"),
    ;

    private String value;

    private Message(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
