package com.maple.filter;

import com.maple.entities.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "CommonFilter", urlPatterns = "/*")
public class CommonFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        log.info("LoginFilter initialized.");
    }

    public void destroy() {
        log.info("LoginFilter destroyed.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("URI: {}", req.getRequestURI());
        log.info("URL: {}", req.getRequestURL());
        log.info("Servlet Path: {}", req.getServletPath());
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (null != user && req.getRequestURI().endsWith("/")) {
            log.info("The request URI is {}. User {} already logged in. Jump to homepage.", req.getRequestURI(), user.getUsername());
            req.getRequestDispatcher("/static/html/Home.html").forward(request, response);
        } else if (null == user && (req.getRequestURI().endsWith("/") || req.getServletPath().startsWith("/static") || req.getServletPath().startsWith("/account"))) {
            chain.doFilter(request, response);
        } else if (null != user) {
            chain.doFilter(request, response);
        }
    }
}
