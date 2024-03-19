package com.example.springbootjwtsecurity3.api;

import com.example.springbootjwtsecurity3.dto.response.DirectorResponse;
import com.example.springbootjwtsecurity3.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/director")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class DirectorApi {
    private final DirectorService directorService;

    @PostMapping("save")
    public DirectorResponse save(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String email,
                                 @RequestParam int age) {
        return directorService.save(firstName, lastName, email, age);
    }

    @GetMapping("find/by/{id}")
    public DirectorResponse findById(@PathVariable Long id) {
        return directorService.findById(id);
    }

    @GetMapping("find/all")
    public List<DirectorResponse> findAll() {
        return directorService.findAll();
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return directorService.deleteById(id);
    }
}