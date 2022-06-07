package com.maple.web;

import com.maple.service.RegisterService;
import com.maple.service.impl.RegisterServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet(urlPatterns = "/account/selectUserByUserName", name = "SelectUserByUserNameServlet")
public class SelectUserByUserNameServlet extends HttpServlet {

    private RegisterService registerService;

    @Override
    public void init() throws ServletException {
        log.info("SelectUserServlet initialized.");
        registerService = new RegisterServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        PrintWriter writer = resp.getWriter();
        boolean verify = registerService.verifyUserName(username);
        writer.write("" + verify);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
