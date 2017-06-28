package com.conf.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:Mr.Cris
 * Date:2017-06-24 22:49
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String name() default "";

    String master = "masterDataSource";

    String slave1 = "slaveDataSource";

}
