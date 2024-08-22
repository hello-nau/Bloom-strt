package com.hcc.controllers.responses;

public class LoginResponse {
    private String token;
    private boolean isValid;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
