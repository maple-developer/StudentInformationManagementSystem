package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.service.StudentService;
import com.maple.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebServlet(name = "TotalItemServlet", urlPatterns = "/stu/total-item")
public class TotalItemServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        log.info("TotalItemServlet initialized.");
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer totalItem = studentService.getTotalItem();
        log.info("Total Item: {}", totalItem);
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(true);
        feedBack.setData(String.valueOf(totalItem));
        response.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("TotalItemServlet destroyed.");
    }
}
