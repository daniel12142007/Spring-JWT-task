package com.example.springbootjwtsecurity.dto.response;

import com.example.springbootjwtsecurity.model.enums.Gender;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String username;
    private String description;
    private String email;
    private Gender gender;
    private String role;
}