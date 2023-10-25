package com.example.springbootjwtsecurity.service;

import com.example.springbootjwtsecurity.config.JwtUtils;
import com.example.springbootjwtsecurity.dto.request.RegisterUserRequest;
import com.example.springbootjwtsecurity.dto.response.JWTResponse;
import com.example.springbootjwtsecurity.model.User;
import com.example.springbootjwtsecurity.model.enums.Role;
import com.example.springbootjwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// TODO: 11
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("not found email:" + request.getEmail() + " email");
        userRepository.save(user);
        jwtUtils.generateToken(user.getEmail());
        return "Registered";
    }

    public JWTResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("not found:" + email + " email");
        });
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.encode(password));
        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtils.generateToken(user.getEmail());
        return new JWTResponse(
                user.getEmail(),
                token,
                "login",
                user.getRole()
        );
    }
}