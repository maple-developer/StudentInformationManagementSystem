package com.maple.web;

import com.alibaba.fastjson.JSON;
import com.maple.entities.FeedBack;
import com.maple.service.EmailService;
import com.maple.service.VerifyCodeService;
import com.maple.service.impl.EmailServiceImpl;
import com.maple.service.impl.VerifyCodeServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/account/code/send_auth_mail", name = "SendAuthenticationEmailServlet")
public class SendAuthenticationEmailServlet extends HttpServlet {

    private VerifyCodeService verifyCodeService;
    private EmailService emailService;

    @Override
    public void init() throws ServletException {
        log.info("SendAuthenticationEmail initialized.");
        verifyCodeService = new VerifyCodeServiceImpl();
        emailService = new EmailServiceImpl();
    }

    @Override
    public void destroy() {
        log.info("SendAuthenticationEmail destroyed.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        log.info("Email: {}", email);
        // Send verification code to email for verify registration.
        String code = verifyCodeService.generateVerificationCode();
        boolean success = emailService.sendEmailToVerify(email, code);
        FeedBack feedBack = new FeedBack();
        feedBack.setFlag(success);
        feedBack.setInfo("Send authentication email to the email address "
                + email + (success ? " successfully." : " failure. May be error occurred for the server.")
        );
        HttpSession session = request.getSession();
        session.setAttribute("verify_code", code);
        log.info("{}", feedBack);
        response.getWriter().write(JSON.toJSONString(feedBack));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
