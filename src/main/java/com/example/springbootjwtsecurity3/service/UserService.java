package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.config.JwtUtils;
import com.example.springbootjwtsecurity3.dto.request.RequestForRegister;
import com.example.springbootjwtsecurity3.model.User;
import com.example.springbootjwtsecurity3.model.enums.Role;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender sender;
//    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, JavaMailSender sender,
//                       AuthenticationManager authenticationManager,
                       JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sender = sender;
//        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<String> create(RequestForRegister request){
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException(
                    "this email is already have in!"
            );
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword( bCryptPasswordEncoder.encode( request.getPassword() ) );
        user.setName( request.getName() );
        user.setRole( Role.GUEST );
        user.setCode(request.getCode());
        userRepository.save(user);
        String generatedToken = jwtUtils.generateToken(request.getEmail());
        return ResponseEntity.ok(generatedToken);
    }

    public String confirmEmailSender(String email){
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String code = randomNumber();
        try {
            helper.setTo(email);
            helper.setSubject("Code for confirming");
            helper.setText(code);
            Optional<User> user = userRepository.findByEmail(email);
            user.get().setCode(code);
            userRepository.save(user.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        sender.send(message);
        return code;
    }

    public String confirmEmail(String code){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        boolean confirming = user.get().getCode().equals(code);
        if (confirming) {
            user.get().setCode(null);
            userRepository.save(user.get());
            return "confirmed ✅";
        }else {
            return "invalid code!";
        }
    }

    public String randomNumber(){
        Random random = new Random();
        return String.valueOf(random.nextInt(0, 9)) +
               random.nextInt(0, 9) +
               random.nextInt(0, 9) +
               random.nextInt(0, 9) +
               random.nextInt(0, 9) +
               random.nextInt(0, 9);
    }
    public String updateRole(String code){
        if (confirmEmail(code).equals("confirmed ✅")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<User> user = userRepository.findByEmail(authentication.getName());
            user.get().setRole(Role.USER);
            userRepository.save(user.get());
            return "Role successfully updated to USER!";
        }else {
            return "invalid code!!!";
        }
    }

    public String forgotPassword(String code,String password){
        Optional<User> user = userRepository.findByCode(code);
        if (user.get().getCode().equals(code)){
        user.get().setPassword(bCryptPasswordEncoder.encode(password));
        user.get().setCode(null);
        userRepository.save(user.get());
        return "password successfully changed !!!";
        }else {
            return "Invalid code!";
        }

    }


}