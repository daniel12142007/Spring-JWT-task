package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.service.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApi {
    private final Auth auth;

    @PostMapping("save")
    public String save(@RequestParam String email, @RequestParam String password, @RequestParam String name) {
        auth.save(name, email, password);
        return "ok";
    }

    @PostMapping("login")
    public String save(@RequestParam String email, @RequestParam String password) {
        return auth.login(email, password);
    }

}
