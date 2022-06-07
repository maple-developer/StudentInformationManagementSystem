package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.User;
import com.maple.service.LoginService;
import com.maple.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Slf4j
@WebServlet(urlPatterns = "/account/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        log.info("LoginServlet initialized.");
        loginService = new LoginServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        BufferedReader reader = req.getReader();
        User user = JSON.parseObject(reader.readLine(), User.class);
//        User verify = loginService.verifyUserByUserName(user.getUsername(), user.getPasswd());
        User verify = loginService.loginByEmailAndPassword(user.getEmail(), user.getPasswd());
        FeedBack feedBack = new FeedBack();
        if (null != verify) {
            log.info("{} login at {} successfully", verify, new Date());
            // session tracking. maintain state for user.
            HttpSession session = req.getSession();
            session.setAttribute("user", verify);
            session.setMaxInactiveInterval(60 * 10);
            feedBack.setInfo("Verify Successfully.");
        } else {
            user = loginService.verifyUserByEmail(user.getEmail());
            feedBack.setInfo(null == user ? "User is not exists." : "Password Error");
        }
        feedBack.setFlag(null != verify);
        writer.write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        log.info("LoginServlet destroyed.");
    }
}
