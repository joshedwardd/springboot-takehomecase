package com.example.try_user_service.auth;


import com.example.try_user_service.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.try_user_service.user.User appUser = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("username not found"));
        return new User(appUser.getUsername(), appUser.getPassword(), Collections.emptyList());
    }
}
