package com.gaoap.mvc.customized.response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CustomizedResponseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomizedResponseApplication.class, args);
    }

}
