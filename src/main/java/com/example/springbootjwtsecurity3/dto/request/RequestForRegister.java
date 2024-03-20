package com.example.springbootjwtsecurity3.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RequestForRegister {
    private String name;
    private String email;
    private String password;
    @JsonIgnore
    private String code;
}
