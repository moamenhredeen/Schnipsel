package me.moamenhredeen.kakera.domain;

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
