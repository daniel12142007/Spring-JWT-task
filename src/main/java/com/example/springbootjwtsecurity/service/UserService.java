package com.example.springbootjwtsecurity.service;

import com.example.springbootjwtsecurity.dto.response.UserResponse;
import com.example.springbootjwtsecurity.model.User;
import com.example.springbootjwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserResponse findById(Long id, String myEmail) {
        User user = userRepository.findById(id).orElseThrow();
        return getUserResponse(myEmail, user);
    }

    public UserResponse findByEmailOrUsername(String username, String myEmail) {
        User user = userRepository.findByUsernameOrEmail(username).orElseThrow();
        return getUserResponse(myEmail, user);
    }

    private UserResponse getUserResponse(String myEmail, User user) {
        User my = userRepository.findByEmail(myEmail).orElseThrow();
        UserResponse response = modelMapper.map(user, UserResponse.class);
        response.setCountFollowers(
                userRepository.findByFollowing(user.getEmail()) != null ?
                        userRepository.findByFollowing(user.getEmail()).size() : 0);
        response.setCountFollowing(user.getFollowing() != null ?
                user.getFollowing().size() : 0);
        response.setCountPublication(user.getMyPublication() != null ?
                user.getMyPublication().size() : 0);
        response.setSubscribed(userRepository.findUserFollowing(my.getEmail(), user.getEmail()) != null);
        response.setMessage("Successfully!");
        return response;
    }
}