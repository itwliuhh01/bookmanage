package com.limai.config;

import com.limai.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName WebMvcConfigurer
 * @Description 配置类
 * @Author LiuHaihua
 * @Date 2018/11/12 10:38
 * @Version 1.0
 **/
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    @Qualifier("myAuthorizationInterceptor")
    private AuthorizationInterceptor authorizationInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authorizationInterceptor)
                .excludePathPatterns("/tokens/login","/tokens/logout","/httpClient/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }


}
