package com.baixiaowen.alllearning.interceptor;

import com.baixiaowen.alllearning.exception.BusinessException;
import com.baixiaowen.alllearning.exception.ErrorCodeEnum;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现全局限流拦截器
 */
@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    /**
     * 限流器实力（QPS限制为10）
     */
    private static final RateLimiter rateLimiter
            = RateLimiter.create(2);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!rateLimiter.tryAcquire()){
            log.error("系统已经被限流...");
            throw new BusinessException(ErrorCodeEnum.RATE_LIMIT_ERROR);
        }

        return true;
    }
}
