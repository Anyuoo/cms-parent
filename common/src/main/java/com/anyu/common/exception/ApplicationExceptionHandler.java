package com.anyu.common.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.anyu.common.result.CommonResult;
import com.anyu.common.result.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author Anyu
 * @since 2020/12/28
 */
@RestControllerAdvice
public class ApplicationExceptionHandler implements BlockExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);


    /**
     * 提供对标准Spring MVC异常的处理 {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler handleException()}
     *
     * @param e       the target exception
     * @param request the current request
     */
    @ExceptionHandler({Exception.class, GlobalException.class, BlockException.class})
    public final ResponseEntity<CommonResult<?>> exceptionHandler(Exception e, WebRequest request) {
        logger.error("ExceptionHandler: {}", e.getMessage(), e.fillInStackTrace());
        HttpHeaders headers = new HttpHeaders();
        if (e instanceof GlobalException) {
            return handleGlobalException((GlobalException) e, headers, request);
        }
        //限流异常
        if (e instanceof FlowException) {
            return handleFlowException((FlowException) e, headers, request);
        }
        //熔断异常
        if (e instanceof DegradeException) {
            return handleDegradeException((DegradeException) e, headers, request);
        }
        //热点参数限流异常
        if (e instanceof ParamFlowException) {
            return handleParamFlowException((ParamFlowException) e, headers, request);
        }
        //系统规则异常
        if (e instanceof SystemBlockException) {
            handleExceptionInternal(e, CommonResult.failure(ResultType.SENTINEL_SYS_BLOCK_ERROR), headers, HttpStatus.TOO_MANY_REQUESTS, request);
        }
        //授权不通过
        if (e instanceof AuthorityException) {
            handleExceptionInternal(e, CommonResult.failure(ResultType.SENTINEL_AUTHORITY_ERROR), headers, HttpStatus.TOO_MANY_REQUESTS, request);
        }
        // TODO:  这里可以自定义其他的异常拦截
        return this.handleException(e, headers, request);
    }

    /**
     * 对sentinel 的异常处理，实现{@link BlockExceptionHandler}接口
     *
     * @param e {@link BlockException}
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws BlockException {
        //交由统一异常处理类处理
        throw e;
    }

    /**
     * 对GlobalException类返回返回结果的处理
     */
    protected ResponseEntity<CommonResult<?>> handleGlobalException(GlobalException e, HttpHeaders headers, WebRequest request) {
        CommonResult<?> body = CommonResult.failure(e.getResultCode());
        HttpStatus status = e.getHttpStatus();
        return handleExceptionInternal(e, body, headers, status, request);
    }


    /**
     * 异常类的统一处理
     */
    protected ResponseEntity<CommonResult<?>> handleException(Exception e, HttpHeaders headers, WebRequest request) {
        CommonResult<?> body = CommonResult.failure();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(e, body, headers, status, request);
    }


    /**
     * 对sentinel 限流异常处理 {@link FlowException}
     */
    protected ResponseEntity<CommonResult<?>> handleFlowException(FlowException e, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
        CommonResult<?> body = CommonResult.failure(ResultType.SENTINEL_FLOW_ERROR);
        return handleExceptionInternal(e, body, headers, status, request);

    }

    /**
     * 对sentinel 熔断异常处理 {@link DegradeException}
     */
    protected ResponseEntity<CommonResult<?>> handleDegradeException(DegradeException e, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
        CommonResult<?> body = CommonResult.failure(ResultType.SENTINEL_DEGRADE_ERROR);
        return handleExceptionInternal(e, body, headers, status, request);

    }

    /**
     * 对sentinel 熔断异常处理 {@link ParamFlowException}
     */
    protected ResponseEntity<CommonResult<?>> handleParamFlowException(ParamFlowException e, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;
        CommonResult<?> body = CommonResult.failure(ResultType.SENTINEL_PARAM_FLOW_ERROR);
        return handleExceptionInternal(e, body, headers, status, request);
    }

    /**
     * org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception, java.lang.Object, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
     * <p>
     * A single place to customize the response body of all exception types.
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     */
    protected ResponseEntity<CommonResult<?>> handleExceptionInternal(
            Exception e, CommonResult<?> body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }


}
