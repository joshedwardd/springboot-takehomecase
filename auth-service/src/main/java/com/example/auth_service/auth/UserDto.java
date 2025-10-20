package com.example.auth_service.auth;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer age;
}
