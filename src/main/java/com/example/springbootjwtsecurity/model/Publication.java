package com.example.springbootjwtsecurity.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection
    private List<String> photosAndVideos;
    private LocalDateTime publication_date;
    @OneToMany(mappedBy = "publication")
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "favorites_id")
    private User favorites;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User my_publication;
    @ManyToMany(mappedBy = "likes")
    private List<User> usersLikes;
}