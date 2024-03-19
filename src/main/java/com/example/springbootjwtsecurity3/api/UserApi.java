package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.response.UserResponse;
import com.example.springbootjwtsecurity3.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApi {
    private final AuthService authService;

    @PostMapping("save")
    public UserResponse save(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password) {
        return authService.save(name, email, password);
    }

    @PostMapping("login")
    public UserResponse save(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }
}