package com;


import com.conf.starter.AbstractApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Author:Mr.Cris
 * Date:2017-06-20 22:53
 */

@SpringBootApplication(scanBasePackages = {"com.controller","com.service","com.dao","com.conf","com.entity"},exclude = {DataSourceAutoConfiguration.class})
public class Application extends AbstractApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }


}
