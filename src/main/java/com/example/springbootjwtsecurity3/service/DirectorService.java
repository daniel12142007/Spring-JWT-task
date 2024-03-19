package com.example.springbootjwtsecurity3.service;

import com.example.springbootjwtsecurity3.dto.response.DirectorResponse;
import com.example.springbootjwtsecurity3.model.Director;
import com.example.springbootjwtsecurity3.repository.DirectorRepository;
import com.example.springbootjwtsecurity3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final UserRepository userRepository;

    public DirectorResponse save(String first,
                                 String last,
                                 String email,
                                 int age) {
        Director director = new Director();
        director.setLastName(last);
        director.setFirstName(first);
        director.setAge(age);
        director.setEmail(email);
        return map(directorRepository.save(director));
    }

    public DirectorResponse findById(Long id) {
        return map(directorRepository.findById(id).orElseThrow());
    }

    public List<DirectorResponse> findAll() {
        List<DirectorResponse> list = new ArrayList<>();
        directorRepository.findAll().forEach(
                a -> {
                    list.add(map(a));
                }
        );
        return list;
    }

    public String deleteById(Long id) {
        directorRepository.deleteById(id);
        return "deleted";
    }

    private DirectorResponse map(Director director) {
        return DirectorResponse.builder()
                .id(director.getId())
                .firstName(director.getFirstName())
                .lastName(director.getLastName())
                .email(director.getEmail())
                .age(director.getAge())
                .build();
    }
}
