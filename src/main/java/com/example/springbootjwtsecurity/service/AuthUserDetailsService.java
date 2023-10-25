package com.example.springbootjwtsecurity.service;

import com.example.springbootjwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.springbootjwtsecurity.model.User;

// TODO: 6 
@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> {
            throw new RuntimeException("user with:"+username+" not found");
        });
        return new AuthUserDetails(user);
    }
}