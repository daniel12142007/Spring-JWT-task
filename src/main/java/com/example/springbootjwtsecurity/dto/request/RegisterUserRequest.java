package com.example.springbootjwtsecurity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

// TODO: 9
@Data
@AllArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
}