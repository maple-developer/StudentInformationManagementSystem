package com.maple.service;

public interface UserService {
    boolean resetPassword(String email, String passwd);

    boolean userIsExists(String email);
}
