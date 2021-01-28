package com.anyu.common.result;

/**
 * web求求结果状态码枚举
 *
 * @author Anyu
 * @since 2020/12/24
 */
public enum ResultType implements Result {
    SUCCESS(2000, "操作成功"),
    FAILURE(4000, "操作失败"),
    SENTINEL_FLOW_ERROR(4100, "接口已被限流"),
    SENTINEL_DEGRADE_ERROR(4101, "接口已被熔断"),
    SENTINEL_PARAM_FLOW_ERROR(4102, "热点参数限流"),
    SENTINEL_SYS_BLOCK_ERROR(4103, "系统规则异常"),
    SENTINEL_AUTHORITY_ERROR(4104, "授权不通过"),
    AUTH_FAILURE(4200,"用户认证失败"),
    TOKEN_SIGNATURE_ERROR(4210,"jwt 签名失败"),
    TOKEN_PARSE_ERROR(4211,"jwt 解析异常");

    /**
     * 业务代码
     */
    private int code;
    /**
     * 消息描述
     */
    private String message;

    ResultType(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
