package com.maple.entities;

public class User {
    private int id;
    private String username;
    private String passwd;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User { id: " + this.getId()
                + " username: " + this.getUsername()
                + " password: " + this.getPasswd()
                + " email: " + this.getEmail()
                + " }";
    }
}
