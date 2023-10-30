package com.example.springbootjwtsecurity.dto.response;

import com.example.springbootjwtsecurity.model.enums.Gender;
import com.example.springbootjwtsecurity.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String username;
    private String description;
    private String email;
    private Gender gender;
    private Role role;
    private LocalDateTime date_register;
    private Integer countFollowers;
    private Integer countFollowing;
    private Integer countPublication;
    private boolean subscribed;//потписался
}