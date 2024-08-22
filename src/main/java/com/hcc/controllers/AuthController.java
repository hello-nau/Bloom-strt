package com.hcc.controllers;


import com.hcc.controllers.requests.AuthCredentialsRequest;
import com.hcc.controllers.responses.LoginResponse;
import com.hcc.services.AuthService;
import com.hcc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<LoginResponse> login(@RequestBody AuthCredentialsRequest request) {
        Optional<String> token = authService.authenticate(request.getUsername(), request.getPassword());
        if (token.isEmpty()) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.ok(new LoginResponse(token.get()));
    }

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestHeader("Autorization")
                                                              String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }
        String token = authorizationHeader.substring(7);
        return ResponseEntity.ok(Collections.singletonMap("isValid", isTokenValid(token)));
    }

    private boolean isTokenValid(String token) {
        return jwtUtil.validateToken(token, userDetails);
    }
}

