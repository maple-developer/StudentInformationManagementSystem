package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.entities.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet(name = "GetLoginUserServlet", value = "/account/login_user")
public class GetLoginUserServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        log.info("GetLoginUserServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        User user = (User) session.getAttribute("user");
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(null != user);
        if (null != user) {
            feedBack.setData(JSON.toJSONString(user));
        } else {
            feedBack.setInfo("No user login to this system.");
        }
        writer.write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("GetLoginUserServlet destroyed.");
    }
}
