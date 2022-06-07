package com.maple.service;

public interface EmailService {

    boolean sendEmailToVerify(String to, String context);

}
