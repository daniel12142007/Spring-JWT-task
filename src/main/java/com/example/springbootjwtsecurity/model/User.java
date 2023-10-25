package com.example.springbootjwtsecurity.model;

import com.example.springbootjwtsecurity.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// TODO: 2
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email) {
    }

    public User() {

    }
}
