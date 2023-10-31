package com.example.springbootjwtsecurity.api;

import com.example.springbootjwtsecurity.dto.request.UserUpdateRequest;
import com.example.springbootjwtsecurity.dto.response.UserResponse;
import com.example.springbootjwtsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if (email == null)
            return new UserResponse("you are not logged in to your account");
        return (!username.equals("null")) ? userService.findByEmailOrUsername(
                username, email
        ) : null;
    }

    @PutMapping("update")
    public UserResponse update(@RequestBody UserUpdateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if (email == null)
            return new UserResponse("you are not logged in to your account");
        return userService.updateUserByEmail(request, email);
    }

    @GetMapping("my/profile")
    public UserResponse myProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if (email == null)
            return new UserResponse("you are not logged in to your account");
        return userService.myProfile(email);
    }
}