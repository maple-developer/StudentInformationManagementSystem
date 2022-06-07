package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebServlet(name = "SignOutServlet", value = "/account/sign_out")
public class SignOutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        log.info("SignOutServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        log.info("Logged out User : {}", user);
        session.invalidate();
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(null != user);
        feedBack.setInfo(null != user ? "sign out successfully." : "sign out failure.");
        response.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("SignOutServlet destroyed.");
    }
}
