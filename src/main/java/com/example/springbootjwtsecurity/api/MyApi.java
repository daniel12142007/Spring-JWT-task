package com.example.springbootjwtsecurity.api;

import com.example.springbootjwtsecurity.dto.request.RegisterUserRequest;
import com.example.springbootjwtsecurity.dto.response.JWTResponse;
import com.example.springbootjwtsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

// TODO: 12
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/my")
public class MyApi {
    private final AuthService authService;

    @PostMapping("/register")
    @PermitAll
    public String register(@RequestBody RegisterUserRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @PermitAll
    public JWTResponse login(@RequestParam String email,
                             @RequestParam String password) {
        return authService.login(email, password);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String admin() {
        return "Hello admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String user() {
        return "Hello user";
    }
}