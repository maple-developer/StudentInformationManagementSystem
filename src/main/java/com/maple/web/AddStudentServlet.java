package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.Student;
import com.maple.service.StudentService;
import com.maple.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/stu/add", name = "AddStudentServlet")
public class AddStudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        log.info("AddStudentServlet initialized.");
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // the data sent by asynchronous request can not get from getParameter method.
        // only read from BufferedReader object got by request object.
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Student student = JSON.parseObject(json, Student.class);
        log.info("write data {} to database successfully.", student);
        boolean flag = studentService.addStudent(student);
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(flag);
        feedBack.setInfo(flag ? "Successful" : "Failure");
        resp.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        log.info("AddStudentServlet destroyed.");
    }
}
