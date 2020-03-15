package com.pisces.spring.boot.learning.config;

import com.alibaba.fastjson.JSON;
import com.pisces.spring.boot.learning.common.exception.BusinessException;
import com.pisces.spring.boot.learning.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: pisces
 * @Description:
 * @Date: Create in 16:58 2020/3/3
 * @Modified By:
 */
@Slf4j
@Aspect
@Component
public class ControllerInterceptor {
    private final static int RETRY_ATTEMPTS = 30;


    @Pointcut("execution(* com.pisces.spring.boot.learning.controller..*(..))")
    public void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        String methodName =
                pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
        String args = Arrays.toString(pjp.getArgs());
        Signature signature = pjp.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();
        int retryTimes = 0;

        while (retryTimes++ < RETRY_ATTEMPTS) {
            try {
                Object object = pjp.proceed();
                if (object != null) {
//                    log.info("finish method:{},param:{},result:{}", methodName, args, JSON.toJsonString(object));
                }
                return object;
            } catch (BusinessException e) {
                log.warn("BusinessException methodName:{} args:{} message:{}", methodName, args, e.getMessage());
                String s = JSON.toJSONString(Response.error(e.getErrorType()));
                return JSON.parseObject(s, returnType);
            } catch (Throwable throwable) {
                log.error(throwable.getMessage(), throwable);
                String s = JSON.toJSONString(Response.error(throwable.getMessage()));
                return JSON.parseObject(s, returnType);
            }
        }
        log.error("retry times than max error! method:{} args:{}", methodName, args);
        String s = JSON.toJSONString(Response.error());
        return JSON.parseObject(s, returnType);
    }

}
