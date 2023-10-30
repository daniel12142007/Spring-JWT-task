package com.example.springbootjwtsecurity.service;

import com.example.springbootjwtsecurity.dto.response.UserResponse;
import com.example.springbootjwtsecurity.model.User;
import com.example.springbootjwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserResponse following(String myEmail, String email) {
        User my = findByEmail(myEmail);
        my.getFollowing().add(findByEmail(email));
        return userService.findByEmailOrUsername(email, myEmail);
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
                    throw new RuntimeException("not found email:" + email);
                }
        );
    }
}