package com.anyu.common.exception;

import com.anyu.common.result.ResultType;
import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {
    /**
     * 结果枚举
     */
    private ResultType resultType;
    /**
     * http 操作状态
     */
    private HttpStatus httpStatus;

    private GlobalException(ResultType resultType, HttpStatus httpStatus) {
        super(resultType.getMessage());
        this.resultType = resultType;
        this.httpStatus = httpStatus;
    }

    /**
     * 通过{@link ResultType}构建异常
     *
     * @param result 结果枚举类
     */
    public static GlobalException causeBy(ResultType result) {
        return new GlobalException(result, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public static GlobalException causeBy(ResultType result, HttpStatus httpStatus) {
        return new GlobalException(result, HttpStatus.SERVICE_UNAVAILABLE);
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ResultType getResultCode() {
        return resultType;
    }
}
