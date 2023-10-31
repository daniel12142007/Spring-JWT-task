package com.example.springbootjwtsecurity.service;

import com.example.springbootjwtsecurity.dto.request.UserUpdateRequest;
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

    public UserResponse myProfile(String email) {
        return getUserResponse(email);
    }

    public UserResponse updateUserByEmail(UserUpdateRequest update, String email) {
        User user = findByEmail(email);
        user.setFullName(update.getFullName() != null ? update.getFullName() : user.getFullName());
        if (userRepository.existsByUsername(update.getUsername()) && !update.getUsername().equals(user.getUsername()))
            return new UserResponse("found username:" + update.getUsername() + " username");
        if (userRepository.existsByEmail(update.getEmail()) && !email.equals(update.getEmail()))
            return new UserResponse("found email:" + update.getEmail() + " email");
        user.setUsername(update.getUsername());
        user.setDescription(update.getDescription() != null ? update.getDescription() : user.getDescription());
        user.setEmail(update.getEmail());
        user.setGender(update.getGender() != null ? update.getGender() : user.getGender());
        userRepository.save(user);
        return getUserResponse(email);
    }

    private UserResponse getUserResponse(String myEmail, User user) {
        User my = findByEmail(myEmail);
        return getUserResponseFromUser(my, user);
    }

    private UserResponse getUserResponse(String myEmail) {
        User my = findByEmail(myEmail);
        return getUserResponseFromUser(my, my);
    }

    private UserResponse getUserResponseFromUser(User my, User user) {
        UserResponse response = modelMapper.map(user, UserResponse.class);
        response.setCountFollowers(
                userRepository.findByFollowing(user.getEmail()).size());
        response.setCountFollowing(user.getFollowing().size());
        response.setCountPublication(user.getMyPublication().size());
        if (!my.getEmail().equals(user.getEmail())) {
            response.setSubscribed(userRepository.findUserFollowing(my.getEmail(), user.getEmail()) != null);
        }
        response.setMessage("Successfully!");
        return response;
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
                    throw new RuntimeException("not found email:" + email);
                }
        );
    }
}