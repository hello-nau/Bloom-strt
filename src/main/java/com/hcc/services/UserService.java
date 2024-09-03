package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public Optional<User> getUser(String username) {
        return Optional.of(userRepository.findByUsername(username).get());
    }
    public void createUser(String username, String password) {
        User user = new User();
        if(userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("This username already exists.");
        }
        user.setUsername(username);
        user.setPassword(passwordEncoder.getPasswordEncoder().encode(password));
        userRepository.save(user);
    }
    public void deleteUser(User user) {
        //TODO add the role check so not everyone can delete a user
        userRepository.delete(user);
    }
}
