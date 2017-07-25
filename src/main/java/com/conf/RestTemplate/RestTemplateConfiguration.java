package com.conf.RestTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Author:Mr.Cris
 * Date:2017-07-15 11:13
 */

@Configuration
public class RestTemplateConfiguration {
    @Bean()
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }




}
