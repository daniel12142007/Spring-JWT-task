package com.example.springbootjwtsecurity3.dto.response;

import com.example.springbootjwtsecurity3.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private String email;
    private String jwt;
    private Role role;
}