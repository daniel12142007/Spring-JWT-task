package com.example.springbootjwtsecurity.model;

import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private LocalDateTime date_comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
}