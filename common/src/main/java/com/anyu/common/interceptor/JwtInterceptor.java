package com.anyu.common.interceptor;

import com.anyu.common.exception.GlobalException;
import com.anyu.common.jwt.JwtProperties;
import com.anyu.common.jwt.JwtService;
import com.anyu.common.jwt.annotation.JwtToken;
import com.anyu.common.result.ResultType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * jwt 拦截器
 * @author Anyu
 * @since 2020/12/30
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //获取拦截到的方法的对象
            Method targetMethod =   ((HandlerMethod) handler).getMethod();
            logger.info("{}#{}方法，进入jwt 拦截器校验",targetMethod.getDeclaringClass(),targetMethod.getName());
            //该方法是否存在注解JwtToken
            if (!targetMethod.isAnnotationPresent(JwtToken.class)) {
                return true;
            }
            JwtToken jwtToken = targetMethod.getAnnotation(JwtToken.class);
            //需要token
            if (jwtToken.required()) {
                //获取token 并核实
                String authToken = request.getHeader(jwtProperties.getAuthHeaderKey());
                //包含请求的token
                if (StringUtils.isNotBlank(authToken)
                        && StringUtils.startsWith(authToken, jwtProperties.getTokenPrefix())
                        &&jwtService.isAvailable(authToken.substring(7))) {
                    return Boolean.TRUE;
                }
                //token 不可用
                throw GlobalException.causeBy(ResultType.AUTH_FAILURE, HttpStatus.UNAUTHORIZED);
            }
        }
        return true;
    }
}
