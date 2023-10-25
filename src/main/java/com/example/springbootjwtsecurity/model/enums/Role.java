package com.example.springbootjwtsecurity.model.enums;

import org.springframework.security.core.GrantedAuthority;

// TODO: 1 
public enum Role implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
