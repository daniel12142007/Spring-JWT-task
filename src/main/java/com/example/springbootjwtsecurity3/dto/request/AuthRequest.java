package com.example.springbootjwtsecurity3.dto.request;

import com.example.springbootjwtsecurity3.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

        private String email;
        private String password;
        @JsonIgnore
        private Role role;
}