package com.maple.service;

import com.maple.entities.User;

public interface RegisterService {

    boolean verifyUserName(String username);

    boolean register(User user);

    boolean verifyEmail(String email);


//    boolean sendEmailToVerify(String to, String context);
}
