package com.hcc.services;

 import com.hcc.entities.User;
// import com.hcc.repositories.UserRepository;
// import com.hcc.utils.CustomPasswordEncoder;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


// uncomment this class once you have created all of the needed parts
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//    @Autowired
//    CustomPasswordEncoder passwordEncoder;

//    @Autowired
    // UserRepository userRepo;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOpt = userRepo.findByUsername(username);
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.getPasswordEncoder().encode("asdfasdf"));
//        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
//    }
//}

 import com.hcc.entities.User;
 import com.hcc.utils.CustomPasswordEncoder;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    CustomPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.getPasswordEncoder().encode("asdfasdf"));
        return user;
    }
}
