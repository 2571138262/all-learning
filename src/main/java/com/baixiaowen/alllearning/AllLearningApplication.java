package com.baixiaowen.alllearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 用来标示我们自定义的filter过滤器可以被SpringBoot扫描到
public class AllLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllLearningApplication.class, args);
    }

}
