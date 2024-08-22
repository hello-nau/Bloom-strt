package com.hcc.controllers;


import com.hcc.controllers.requests.AuthCredentialsRequest;
import com.hcc.services.AuthService;
import com.hcc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// this is an example controller feel free to delete this once you have created your own.
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    UserDetails userDetails;

    @PostMapping("/login")
    public ResponseEntity<?> generateUserToken(@RequestBody AuthCredentialsRequest request) {
        Optional<String> token = authService.authenticate(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(new JWTUtil());
    }
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken() {
        jwtUtil.validateToken(token, userDetails);
    }



//    @GetMapping
//    ResponseEntity<?> welcomeLearner() {
//        Hello greeting = helloService.greetLearner();
//        return ResponseEntity.ok(greeting);
//    }
}
