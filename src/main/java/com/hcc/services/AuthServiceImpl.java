package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTUtil jwtUtil;
    @Override
    public Optional<String> authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword()))
            return Optional.empty();

        return Optional.of(jwtUtil.generateToken(user.get()));
    }
}
