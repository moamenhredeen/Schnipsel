package me.moamenhredeen.kakera.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String content;

    @ManyToOne()
    private User author;

    @ManyToOne()
    private Snippet snippet;
}
