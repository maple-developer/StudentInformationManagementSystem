package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.service.VerifyCodeService;
import com.maple.service.impl.VerifyCodeServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/account/code/verify_code", name = "VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {

    private VerifyCodeService verifyCodeService;

    @Override
    public void init() throws ServletException {
        log.info("VerifyCodeServlet initialized.");
        verifyCodeService = new VerifyCodeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        // sent by client
        String verify_code = request.getParameter("verify_code");
        HttpSession session = request.getSession();
        // get the correct code from server
        String code = "";
        if (null != session) {
            code = (String) session.getAttribute("verify_code");
            log.info("Get verification code from server: {}", code);
        }
        log.info("verification code enter by client: {}, server code: {}", verify_code, code);
        boolean verify = verifyCodeService.verifyVerificationCode(verify_code, code);
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(verify);
        feedBack.setInfo(verify ? "Verification Code Matched." : "Verification Code Error.");
        response.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("VerifyCodeServlet destroyed.");
    }
}
