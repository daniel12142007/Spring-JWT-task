package com.example.springbootjwtsecurity3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}