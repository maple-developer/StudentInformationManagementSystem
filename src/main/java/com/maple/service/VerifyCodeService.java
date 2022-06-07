package com.maple.service;

import javax.servlet.ServletOutputStream;

public interface VerifyCodeService {
    boolean verifyVerificationCode(String code, String correct);

    String generateVerificationCode(ServletOutputStream servletOutputStream);

    String generateVerificationCode();
}
