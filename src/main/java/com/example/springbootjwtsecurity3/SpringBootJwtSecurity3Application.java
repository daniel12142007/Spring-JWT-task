package com.example.springbootjwtsecurity3;

import com.example.springbootjwtsecurity3.model.Director;
import com.example.springbootjwtsecurity3.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootJwtSecurity3Application {
    private final DirectorRepository directorRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtSecurity3Application.class, args);
    }

    @PostConstruct
    public void init() {
        Director director = new Director();
        director.setFirstName("first");
        director.setLastName("last");
        director.setEmail("email");
        director.setAge(19);
        directorRepository.save(director);
    }
}