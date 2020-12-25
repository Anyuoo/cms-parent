package com.anyu.common.exception;

import com.anyu.common.entity.enums.ResultEnum;
import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {
    /**
     * 异常错误业务代码
     */
    private int code;
    /**
     * http 操作状态
     */
    private HttpStatus httpStatus;

    public GlobalException(int code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public GlobalException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    /**
     * 通过{@link ResultEnum}构建异常
     *
     * @param result 结果枚举类
     */
    public static GlobalException causeBy(ResultEnum result, HttpStatus httpStatus) {
        return new GlobalException(result.getCode(), result.getMessage(), httpStatus);
    }

    /**
     * 通过{@link ResultEnum}构建异常
     *
     * @param result 结果枚举类
     */
    public static GlobalException causeBy(ResultEnum result) {
        return new GlobalException(result.getCode(), result.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }


}
