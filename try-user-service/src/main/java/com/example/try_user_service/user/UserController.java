package com.example.try_user_service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            User registeredUser = userService.registerUser(user);
            registeredUser.setPassword(null);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        }catch(IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.getUserById(id).map(user ->{
            user.setPassword(null);
            return ResponseEntity.ok(user);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        try{
            User updatedUser = userService.updateProfile(id, user);
            updatedUser.setPassword(null);
            return ResponseEntity.ok(updatedUser);
        }catch(IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/internal/{username}")
    public ResponseEntity<User> getInternalUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
