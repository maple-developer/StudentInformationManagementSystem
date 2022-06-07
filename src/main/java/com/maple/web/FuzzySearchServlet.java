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
import java.util.List;

@Slf4j
@WebServlet(name = "FuzzySearchServlet", value = "/stu/fuzzy-search")
public class FuzzySearchServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        log.info("FuzzySearchServlet initialized.");
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = request.getReader().readLine();
        Student student = JSON.parseObject(jsonString, Student.class);
        log.info("{}", student);
        List<Student> students = studentService.fuzzySearchStudent(student);
        String s = JSON.toJSONString(students);
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(true);
        feedBack.setData(s);
        log.info("{}", JSON.toJSONString(feedBack));
        response.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("FuzzySearchServlet destroyed.");
    }
}
