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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String salary;
    @ManyToOne
    @JoinColumn(name = "company_employee_id")
    private Company company;
    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;
}