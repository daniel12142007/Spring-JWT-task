package com.example.springbootjwtsecurity3.model;

import com.example.springbootjwtsecurity3.model.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Orders1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aa;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Task> tasks;
}