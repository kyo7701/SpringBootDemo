package com.conf.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author:Mr.Cris
 * Date:2017-06-23 21:35
 */
public class DBContextHolder {

    public static final Logger logger = LoggerFactory.getLogger(DBContextHolder.class);

    private static final ThreadLocal<String> context = new ThreadLocal();

    public static void setDataSourceType(String type) {
        if (type == null ) throw new NullPointerException();
        context.set(type);
    }

    public static String getDataSourceType() {
        logger.info("dataSource:" +context.get());
        return context.get();
    }

    public static void clearDataSourceType() {
        context.remove();
    }

}
