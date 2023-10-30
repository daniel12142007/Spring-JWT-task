package com.example.springbootjwtsecurity.api;

import com.example.springbootjwtsecurity.dto.response.UserResponse;
import com.example.springbootjwtsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserApi {
    private final UserService userService;

    @GetMapping("find/by/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @GetMapping("find/by/email/or/username")
    public UserResponse findByEmailOrUsername(@RequestParam(defaultValue = "null") String username) {
        return (!username.equals("null")) ? userService.findByEmailOrUsername(username, SecurityContextHolder.getContext().getAuthentication().getName()) : null;
    }
}