package com.anyu.common.result;

import lombok.Getter;

/**
 * 通用结果返回
 *
 * @author Anyu
 * @since 2020/12/24
 */
public  class CommonResult<T> {

    /**
     * 状态码
     */
    private  int code;
    /**
     * 是否成功
     */
    private  boolean success;
    /**
     * 提示信息
     */
    private  String message;
    /**
     * 数据
     */
    private  T data;

    public CommonResult() {
    }

    private CommonResult(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 处理成功结果
     */
    public static <T> CommonResult<T> success(Result result, T data) {
        return new CommonResult<>(Boolean.TRUE, result.getCode(), result.getMessage(), data);
    }

    public static CommonResult<Void> success(Result result) {
        return success(result, null);
    }

    public static <T> CommonResult<T> success(T data) {
        return success(ResultType.SUCCESS, data);
    }

    public static CommonResult<Void> success() {
        return success(ResultType.SUCCESS, null);
    }

    /**
     * 处理失败结果
     */
    public static <T> CommonResult<T> failure(Result result, T data) {
        return new CommonResult<>(Boolean.FALSE, result.getCode(), result.getMessage(), data);
    }

    public static CommonResult<Void> failure(Result result) {
        return failure(result, null);
    }

    public static <T> CommonResult<T> failure(T data) {
        return failure(ResultType.FAILURE, data);
    }

    public static CommonResult<Void> failure() {
        return failure(ResultType.FAILURE, null);
    }

    public int getCode() {
        return code;
    }

    public CommonResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public CommonResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
