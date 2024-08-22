package com.hcc.services;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface AuthService {

    Optional<String> authenticate(String username, String password);
}
//    public AuthService greetLearner() {
//
//        String greeting = "VGltZTJDb2RlIQ==";
//        byte[] decodedBytes = Base64.getDecoder().decode(greeting);
//        String decodedString = new String(decodedBytes);
//        return new Hello(1L, decodedString);
//    }


