package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.request.AuthRequest;
import com.example.springbootjwtsecurity3.dto.request.RequestForRegister;
import com.example.springbootjwtsecurity3.dto.response.AuthResponse;
import com.example.springbootjwtsecurity3.dto.response.UserResponse;
import com.example.springbootjwtsecurity3.model.User;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import com.example.springbootjwtsecurity3.service.AuthService;
import com.example.springbootjwtsecurity3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserApi {
    private final AuthService authService;
    private final UserService userService;
    private final UserRepository userRepository;

//    @PostMapping("save")
//    public UserResponse save(@RequestParam String name,
//                             @RequestParam String email,
//                             @RequestParam String password) {
//        return authService.save(name, email, password);
//    }
//
//    @PostMapping("login")
//    public UserResponse save(@RequestParam String email, @RequestParam String password) {
//        return authService.login(email, password);
//    }


    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<String> create(@RequestBody RequestForRegister userRequest){
        userRequest.setCode(userService.confirmEmailSender(userRequest.getEmail()));
        return userService.create(userRequest);
    }

    @PostMapping("/login")
    @PermitAll
    public UserResponse authenticated(@RequestBody AuthRequest authRequest) {
        Optional<User> user = userRepository.findByEmail(authRequest.getEmail());
        authRequest.setRole(user.orElseThrow().getRole());
        return authService.login(authRequest);
    }

    @PutMapping("/confirm/email/with/{code}")
    public String updateRole(@PathVariable String code){
        return userService.updateRole(code);
    }

    @GetMapping("/send/code/for/update/password")
    public String forgotPassword(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.confirmEmailSender(authentication.getName());
        return "successfully sent";
    }

    @PutMapping("/update/password")
    public String forgotPassword(String code,String password){
        return userService.forgotPassword(code, password);
    }
}