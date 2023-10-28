package com.example.springbootjwtsecurity.api;

import com.example.springbootjwtsecurity.dto.request.RegisterUserRequest;
import com.example.springbootjwtsecurity.dto.response.JWTResponse;
import com.example.springbootjwtsecurity.service.auth.AuthService;
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
    public JWTResponse register(@RequestBody RegisterUserRequest request) {
        if (request.getPassword().length() <= 6) {
            return new JWTResponse("The password must contain at least 6!");
        } else if (request.getEmail().matches("@gmail.com")) {
            return new JWTResponse("The email must contain @gmail.com");
        }
        return authService.register(request);
    }

    @PostMapping("/login")
    @PermitAll
    public JWTResponse login(@RequestParam(defaultValue = "email") String email,
                             @RequestParam(defaultValue = "pas") String password) {
        return (email.equals("email") && password.equals("pas")) ? null : authService.login(email, password);
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