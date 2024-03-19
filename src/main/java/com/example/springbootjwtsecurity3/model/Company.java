package com.example.springbootjwtsecurity3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    @OneToOne
    @JoinColumn(name = "director_id")
    private Director director;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Client> clients;
}