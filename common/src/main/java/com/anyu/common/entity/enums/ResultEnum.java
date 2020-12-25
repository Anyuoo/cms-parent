package com.anyu.common.entity.enums;

/**
 * web求求结果状态码枚举
 *
 * @author Anyu
 * @since 2020/12/24
 */
public enum ResultEnum implements IEnum {
    SUCCESS(2000, "操作成功"),
    ERROR(2001, "操作失败");

    private int code;
    private String message;

    ResultEnum(int code, String message) {
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
