package com.anyu.common.advice.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * controller层标识，结果处理
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
