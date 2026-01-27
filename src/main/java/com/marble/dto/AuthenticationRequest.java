package com.marble.dto;

public class AuthenticationRequest {
    private String mobile;   // changed from username
    private String password;

    public AuthenticationRequest() {}

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

