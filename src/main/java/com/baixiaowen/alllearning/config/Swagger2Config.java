package com.baixiaowen.alllearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置类
 */
@Configuration
@EnableSwagger2 // 开启Swagger2配置
public class Swagger2Config {

    /**
     * Swagger2信息
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // API 基本信息
                .apiInfo(apiInfo())
                // 通过select 设置允许暴露的接口
                .select()
                // 把Controller包完全暴露出去
                .apis(RequestHandlerSelectors
                        .basePackage("com.baixiaowen.alllearning.controller"))
                // 无限制请求的地址都暴露出去
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API 基本信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("ALL-Learning项目")
                .description("综合课程知识点，整合实战项目")
                .contact(new Contact("白晓文", "", ""))
                .version("1.0.0")
                .build();
    }

}
