package com.baixiaowen.alllearning.config;

import com.baixiaowen.alllearning.interceptor.RateLimitInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Web配置类
 */
@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    /**
     * 全局限流拦截器
     */
    @Resource
    private RateLimitInterceptor rateLimitInterceptor;

    /**
     * 向Web中添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截限流器，拦截所有已/api/开头的请求
        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/api/**");
    }

    /**
     * 静态资源配置的，不经过拦截器处理
     * 向Web中添加资源处理的
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 在这里可以将本地的一个文件夹目录映射到服务器上，类似于nginx代理，这里用tomcat做一个静态文件的代理

        // 配置本地文件夹目录映射
        registry
                // 配置文件的映射地址 请求地址
                .addResourceHandler("/uploads/**")
                // 映射的真正的本地地址
                .addResourceLocations("file:D:\\PersonalLearning\\all-learning\\uploads\\");


        // Swagger2做的映射
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
