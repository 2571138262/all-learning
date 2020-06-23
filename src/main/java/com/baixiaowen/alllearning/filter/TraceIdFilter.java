package com.baixiaowen.alllearning.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

/**
 * TraceId过滤器
 */
@Order(1)
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
