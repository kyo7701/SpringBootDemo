package com.conf.datasource;

import com.conf.mybatis.DBContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Author:Mr.Cris
 * Date:2017-06-24 23:00
 */

@Aspect
@Component
public class DataSourceInterceptor implements Ordered {

    public int getOrder() {
        return 0;
    }

    @Around("@annotation(dataSource)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint,DataSource dataSource) {
        Object result = null;
        System.out.println(dataSource.name());
        try {
            String name = dataSource.name();
            if (DataSource.master.equals(name)) {
                DBContextHolder.setDataSourceType(DataSource.master);
            }else {
                DBContextHolder.setDataSourceType(DataSource.slave1);
            }
            result = proceedingJoinPoint.proceed();
            System.out.println("后置通知");
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            DBContextHolder.clearDataSourceType();
        }

        return result;

    }

}
