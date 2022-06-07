package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.Student;
import com.maple.service.StudentService;
import com.maple.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebServlet(name = "FindStudentByIdServlet", value = "/stu/get")
public class FindStudentByIdServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        log.info("FindStudentServlet initialized.");
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        String id = request.getParameter("id");
        log.info("ID: {}", id);
        Student student = studentService.findStudent(id);
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(null != student);
        if (null != student) {
            feedBack.setData(JSON.toJSONString(student));
        } else {
            feedBack.setInfo("This student is not exists.");
        }
        log.info("Json String: {} ", JSON.toJSONString(student));
        log.info("Json String: {} ", JSON.toJSONString(feedBack));
        response.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("FindStudentByIdServlet destroyed.");
    }
}
