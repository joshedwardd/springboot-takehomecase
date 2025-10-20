package com.example.try_user_service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("Username taken");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String name){
        return userRepository.findByUsername(name);
    }

    public User updateProfile(Long id, User userDetails){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException("User not found"));
        user.setName(userDetails.getName());
        user.setAge(userDetails.getAge());
        return userRepository.save(user);
    }
}
