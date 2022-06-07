package com.maple.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "EncodeFilter", urlPatterns = "/*")
public class EncodeFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        log.info("EncodeFilter initialized.");
    }

    public void destroy() {
        log.info("EncodeFilter destroyed.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
