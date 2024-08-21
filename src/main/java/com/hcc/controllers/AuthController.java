package com.hcc.controllers;


import com.hcc.controllers.requests.AuthCredentialsRequest;
import com.hcc.services.AuthService;
import com.hcc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// this is an example controller feel free to delete this once you have created your own.
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> generateUserToken(@RequestBody AuthCredentialsRequest request) {
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
