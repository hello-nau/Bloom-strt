package com.hcc.repositories;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserRepository {
    public Optional<UserDetails> findByUsername(String usernameFromToken) {
    }
}
