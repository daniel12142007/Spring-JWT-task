package com.example.springbootjwtsecurity.api;

import com.example.springbootjwtsecurity.dto.response.UserResponse;
import com.example.springbootjwtsecurity.service.FollowService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/follow")
public class FollowApi {
    private final FollowService followService;

    @PostMapping("following")
    public UserResponse followingUserByEmail(@RequestParam(defaultValue = "null") String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String my = authentication.getName();
        if (my == null) {
            return new UserResponse("You are not logged in to your account!");
        }
        if (email.equals(my)) {
            return new UserResponse("You can't follow yourself!");
        }
        return (!email.equals("null")) ? followService.following(
                my, email
        ) : null;
    }
}