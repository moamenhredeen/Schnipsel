package me.moamenhredeen.kakera.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Snippet> snippets;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
}
