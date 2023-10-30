package com.example.springbootjwtsecurity.dto.request;

import com.example.springbootjwtsecurity.model.enums.Gender;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String username;
    private String description;
    private String email;
    private Gender gender;
}