package com.hcc.controllers.responses;

public class LoginResponse {
    private String token;
    public LoginResponse(){}

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
