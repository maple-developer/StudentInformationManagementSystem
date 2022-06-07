package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.User;
import com.maple.service.RegisterService;
import com.maple.service.impl.RegisterServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet(urlPatterns = "/account/register", name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private RegisterService registerService;

    @Override
    public void init() throws ServletException {
        registerService = new RegisterServiceImpl();
        log.info("RegisterServlet initialized.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        FeedBack feedBack = new FeedBack();
        PrintWriter writer = resp.getWriter();
        BufferedReader reader = req.getReader();
        // request wits async sent by axio can not get parameter from getParameter method.
        // must get from reader object.
        String jsonString = reader.readLine();
        User user = JSON.parseObject(jsonString, User.class);
        log.info("{}", user);
        // Determine if the given email address has a registered user.
        boolean emailIsExists = registerService.verifyEmail(user.getEmail());
        if (emailIsExists) {
            feedBack.setFlag(false);
            feedBack.setInfo("This email have already registered an user.");
            writer.write(JSON.toJSONString(feedBack));
            return;
        }
        boolean register = registerService.register(user);
        if (register) {
            log.info("User {} register successfully.", user.getUsername());
            feedBack.setInfo("Register Successfully.");
        } else {
            log.info("May be error occurred for server.");
            feedBack.setInfo("May be error occurred for server.");
        }
        feedBack.setFlag(register);
        writer.write(JSON.toJSONString(feedBack));
    }

    @Override
    public void destroy() {
        log.info("RegisterServlet destroyed.");
    }
}
