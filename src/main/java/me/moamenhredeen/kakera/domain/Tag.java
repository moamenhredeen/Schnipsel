package me.moamenhredeen.kakera.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(targetEntity = Snippet.class, mappedBy = "tags")
    private List<Snippet> snippets;
}
