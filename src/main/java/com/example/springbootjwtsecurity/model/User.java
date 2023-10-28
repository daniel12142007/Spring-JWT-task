package com.example.springbootjwtsecurity.model;

import com.example.springbootjwtsecurity.model.enums.Role;
import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

// TODO: 2
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String username;
    private String description;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Chat chat;
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
    @OneToMany(mappedBy = "my_publication", cascade = CascadeType.ALL)
    private List<Publication> myPublication;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "favorites")
    private List<Publication> publications;

    @ManyToMany
    @JoinTable(name = "followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "followers_id"))
    private List<User> followers;
    @ManyToMany
    @JoinTable(name = "following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> following;
    @ManyToMany
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "publication_id"))
    private List<Publication> likes;
}