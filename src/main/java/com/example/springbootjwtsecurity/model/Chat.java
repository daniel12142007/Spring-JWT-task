package com.example.springbootjwtsecurity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "chat")
    private List<Notification> notifications;
}
