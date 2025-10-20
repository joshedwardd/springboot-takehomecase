package com.example.auth_service.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final String userServiceUrl;

    public AuthService(RestTemplate restTemplate, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, @Value("${user.service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userServiceUrl = userServiceUrl;
    }

    public String login(String username, String password){
        String url = userServiceUrl+"/internal/" +username;
        UserDto user;
        try{
            user = restTemplate.getForObject(url,UserDto.class);
        }catch(HttpClientErrorException.NotFound e){
            throw new RuntimeException("Invalid username or password");
        }
        if(user == null|| !passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid username or password");
        }
        return jwtUtil.generateToken(user.getUsername());
    }




}
