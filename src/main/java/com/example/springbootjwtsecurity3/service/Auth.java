package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.config.JwtUtils;
import com.example.springbootjwtsecurity3.model.User;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Auth {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public void save(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("user exists email");
        }
        userRepository.save(user);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email).get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("invalid password");
        }
        return jwtUtils.generateToken(email);
    }
}
