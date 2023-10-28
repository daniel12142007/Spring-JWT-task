package com.example.springbootjwtsecurity.service;

import com.example.springbootjwtsecurity.config.JwtUtils;
import com.example.springbootjwtsecurity.dto.request.RegisterUserRequest;
import com.example.springbootjwtsecurity.dto.response.JWTResponse;
import com.example.springbootjwtsecurity.model.Chat;
import com.example.springbootjwtsecurity.model.User;
import com.example.springbootjwtsecurity.model.enums.Role;
import com.example.springbootjwtsecurity.repository.ChatRepository;
import com.example.springbootjwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// TODO: 11
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final ChatRepository chatRepository;

    public JWTResponse register(RegisterUserRequest request) {
        User user = User
                .builder()
                .role(Role.USER)
                .email(request.getEmail())
                .username(request.getUsername())
                .date_register(LocalDateTime.now())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("not found email:" + request.getEmail() + " email");
        userRepository.save(user);
        Chat chat = new Chat();
        chat.setUser(user);
        chatRepository.save(chat);
        String token = jwtUtils.generateToken(user.getEmail());
        return new JWTResponse(
                user.getEmail(),
                token,
                "login",
                user.getRole()
        );
    }

    public JWTResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("not found:" + email + " email");
        });
        if (!passwordEncoder.matches(password, user.getPassword())) {
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