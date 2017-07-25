package com.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Author:Mr.Cris
 * Date:2017-07-18 23:00
 */
@Aspect
@Component
public class AOPLog implements Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    private static final Logger logger = LoggerFactory.getLogger(AOPLog.class);

    @Around("@annotation(log)")
    public Object proceed (ProceedingJoinPoint point,Log log) {
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取request对象
        HttpServletRequest request = attributes.getRequest();
        System.out.println(log.name());
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

}
