package com.example.kok.config;


import com.example.kok.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Interceptor())
//                .addPathPatterns("/test/**", "a/list", "a/detail");
//                .excludePathPatterns("/test/**");
    }
}
