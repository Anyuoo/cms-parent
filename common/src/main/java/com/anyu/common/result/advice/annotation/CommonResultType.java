package com.anyu.common.result.advice.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * controller层标识，如果类或者方法被注解，{@link com.anyu.common.result.advice.RestCommonResultAdvice}会对方法的返回值进行统一结果处理
 *
 * @author Anyu
 * @since 2020/12/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface CommonResultType {
}
