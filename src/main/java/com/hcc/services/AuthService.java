package com.hcc.services;


import com.hcc.repositories.UserRepository;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// this is an example service feel free to delete this once you have created your own.
@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

//    public AuthService greetLearner() {
//
//        String greeting = "VGltZTJDb2RlIQ==";
//        byte[] decodedBytes = Base64.getDecoder().decode(greeting);
//        String decodedString = new String(decodedBytes);
//        return new Hello(1L, decodedString);
//    }

}
