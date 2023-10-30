package com.example.springbootjwtsecurity.model;

import com.example.springbootjwtsecurity.model.enums.Gender;
import com.example.springbootjwtsecurity.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// TODO: 2
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String username;
    private String description;
    private String email;
    private String password;
    private LocalDateTime date_register;
    private boolean public_account = false;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Gender gender;
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

    @OneToMany(mappedBy = "request_followers")
    private List<User> requestFollowers;
    @ManyToOne
    @JoinColumn(name = "subscription_request_id")
    private User request_followers;
    //    @ManyToMany
//    @JoinTable(name = "followers",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "followers_id"))
//    private List<User> followers;
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