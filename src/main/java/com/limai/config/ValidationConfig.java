package com.limai.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @ClassName ValidationConfig
 * @Description 注入参数校验配置bean
 * @Author LiuHaihua
 * @Date 2018/11/8 18:50
 * @Version 1.0
 **/
@Configuration
@EnableAutoConfiguration
public class ValidationConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }

}
