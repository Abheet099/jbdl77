package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfig {

    private static Logger logger = LoggerFactory.getLogger(CustomConfig.class);

    @Bean
    public RestTemplate getTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        logger.info("In CustomConfig.getTemplate: {}", restTemplate);
        return restTemplate;
    }

    @Bean
    public RestTemplate getTemplate2() {
        RestTemplate restTemplate = new RestTemplate();
        logger.info("In CustomConfig.getTemplate2: {}", restTemplate);
        return restTemplate;
    }
}
