package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.User;
import com.maple.service.UserService;
import com.maple.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebServlet(name = "ResetPasswordServlet", urlPatterns = "/account/reset-passwd")
public class ResetPasswordServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        log.info("ResetPasswordServlet initialized.");
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonString = request.getReader().readLine();
        User user = JSON.parseObject(jsonString, User.class);
        String email = user.getEmail();
        boolean exists = userService.userIsExists(email);
        FeedBack feedBack = new FeedBack();
        if (!exists) {
            feedBack.setFlag(false);
            feedBack.setInfo("You have not register account using this email.");
        } else {
            boolean success = userService.resetPassword(email, user.getPasswd());
            feedBack.setFlag(success);
            feedBack.setInfo(success ? "Reset password successfully" : "Reset password failure");
        }
        response.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("ResetPasswordServlet destroyed.");
    }
}
