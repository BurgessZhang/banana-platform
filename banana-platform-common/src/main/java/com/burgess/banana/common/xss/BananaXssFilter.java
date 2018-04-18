package com.burgess.banana.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.xss
 * @file_name BananaXssFilter.java
 * @description Xss过滤
 * @create 2018-04-18 22:44
 */
public class BananaXssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        BananaXssHttpServletRequestWrapper xssRequest = new BananaXssHttpServletRequestWrapper(
                (HttpServletRequest) servletRequest);
        filterChain.doFilter(xssRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
