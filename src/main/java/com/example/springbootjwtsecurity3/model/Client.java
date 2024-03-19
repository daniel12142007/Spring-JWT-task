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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int countOrders;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Orders> orders;
    @ManyToOne
    @JoinColumn(name = "company_client_id")
    private Company company;
}