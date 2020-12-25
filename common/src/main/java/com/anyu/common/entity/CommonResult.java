package com.anyu.common.entity;

import com.anyu.common.entity.enums.ResultEnum;
import lombok.Getter;

import java.io.Serializable;

/**
 * 通用结果返回
 *
 * @author Anyu
 * @since 2020/12/24
 */
@Getter
public final class CommonResult<T> implements Serializable {

    /**
     * 状态码
     */
    private final int code;
    /**
     * 是否成功
     */
    private final boolean success;
    /**
     * 提示信息
     */
    private final String message;
    /**
     * 数据
     */
    private final T data;

    /**
     * 成功处理结果
     */
    private CommonResult(int code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success(int code, String message, T data) {
        return new CommonResult<>(code, Boolean.TRUE, message, data);
    }

    public static <T> CommonResult<T> success(int code, String message) {
        return success(code, message, null);
    }

    public static <T> CommonResult<T> success(T data) {
        return success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> success() {
        return success(null);
    }

    /**
     * 错误处理结果
     */
    public static <T> CommonResult<T> error(int code, String message, T data) {
        return new CommonResult<>(code, Boolean.FALSE, message, data);
    }

    public static <T> CommonResult<T> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> CommonResult<T> error(T data) {
        return error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage(), data);
    }

    public static CommonResult<Void> error() {
        return error(null);
    }
}
