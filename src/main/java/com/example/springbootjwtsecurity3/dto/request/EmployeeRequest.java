package com.example.springbootjwtsecurity3.dto.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String salary;
}