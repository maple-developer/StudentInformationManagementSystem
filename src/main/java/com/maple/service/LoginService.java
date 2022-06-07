package com.maple.service;

import com.maple.entities.User;

public interface LoginService {

   User loginByUserNameAndPassword(String username, String passwd);

   User verifyUserByUserName(String username);

   User loginByEmailAndPassword(String email, String passwd);

   User verifyUserByEmail(String email);
}
