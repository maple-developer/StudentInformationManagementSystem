package com.maple.web;

import com.maple.service.VerifyCodeService;
import com.maple.service.impl.VerifyCodeServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/account/code/get_code", name = "GenerateVerificationCodeServlet")
public class GenerateVerificationCodeServlet extends HttpServlet {

    private VerifyCodeService verifyCodeService;

    @Override
    public void init() throws ServletException {
        log.info("GenerateVerificationCodeServlet initialized.");
        verifyCodeService = new VerifyCodeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("image/jpeg;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        HttpSession session = request.getSession();
        String code = verifyCodeService.generateVerificationCode(outputStream);
        log.info("send verification code {} to client.", code);
        session.setAttribute("verify_code", code);
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        log.info("GenerateVerificationCode destroyed.");
    }
}
