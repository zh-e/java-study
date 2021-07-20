package com.zhe.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointConfig {

    @Value("${execution}")
    public static String execution;

    public static String getExecution() {
        return execution;
    }

}
