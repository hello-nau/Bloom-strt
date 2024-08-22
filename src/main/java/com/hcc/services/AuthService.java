package com.hcc.services;


import com.hcc.entities.User;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

// this is an example service feel free to delete this once you have created your own.
@Service
public interface AuthService {

    Optional<String> authenticate(String username, String password);
//    public AuthService greetLearner() {
//
//        String greeting = "VGltZTJDb2RlIQ==";
//        byte[] decodedBytes = Base64.getDecoder().decode(greeting);
//        String decodedString = new String(decodedBytes);
//        return new Hello(1L, decodedString);
//    }

}
