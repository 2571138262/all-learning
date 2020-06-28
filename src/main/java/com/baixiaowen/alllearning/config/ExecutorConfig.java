package com.baixiaowen.alllearning.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 */
@Configuration
@EnableAsync // 打开异步功能
@Slf4j
public class ExecutorConfig {

    /**
     * 定义导出服务线程池
     *
     * @return
     */
    @Bean("exportServiceExecutor")
    public Executor exportServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 核心线程数量 : 当前机器的核心数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());

        // 最大线程数
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);

        // 队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);

        // 线程池中线程名前缀
        executor.setThreadNamePrefix("export-");

        // 设置线程池的拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        // 执行初始化
        executor.initialize();

        return executor;
    }

}
