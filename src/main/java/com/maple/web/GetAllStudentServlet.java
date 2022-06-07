package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.Student;
import com.maple.entities.User;
import com.maple.service.StudentService;
import com.maple.service.impl.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = "/stu/get_all", name = "GetAllStudentServlet")
public class GetAllStudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        log.info("SelectAllStudentServlet is running.");
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        List<Student> students = studentService.selectAll();
        String s = JSON.toJSONString(students);
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(students.size() > 0);
        feedBack.setData(students.size() > 0 ? s : null);
        writer.write(JSON.toJSONString(feedBack));
    }

    @Override
    public void destroy() {
        log.info("SelectAllStudentServlet destroyed.");
    }
}
