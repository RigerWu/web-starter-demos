package com.rigerwu.dubbo.provider.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * created by riger on 2021/2/5
 */
@Aspect
@Component
@Slf4j
public class ExceptionHandler {
    @Pointcut("execution(* com.rigerwu.dubbo.provider.service.*.*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("channel {}: receive: \n{}", methodName, args[0].toString());
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = joinPoint.proceed();
        } catch (Throwable e) {
            // let GlobalExceptionHandler handle Exceptions
            log.error("channel {}: got error!!!", methodName);
            throw e;
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        log.info("channel {}: sendback(time:{}): \n{}", methodName, time, result != null ? result.toString() : null);
        return result;
    }
}
