package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.config.JwtUtils;
import com.example.springbootjwtsecurity3.dto.request.AuthRequest;
import com.example.springbootjwtsecurity3.dto.response.UserResponse;
import com.example.springbootjwtsecurity3.model.User;
import com.example.springbootjwtsecurity3.model.enums.Role;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserResponse save(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(password));
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("user exists email");
        }
        return map(userRepository.save(user));
    }

    public UserResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("invalid password");
        }
        return map(user);
    }

    private UserResponse map(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .role(user.getRole())
                .name(user.getName())
                .email(user.getEmail())
                .jwtToken(jwtUtils.generateToken(user.getEmail()))
                .build();
    }
}