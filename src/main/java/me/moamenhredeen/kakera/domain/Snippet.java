package me.moamenhredeen.kakera.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "snippets")
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;
    private String content;
    private String language;

    @ManyToOne()
    private User author;

    @OneToMany(mappedBy = "snippet")
    private List<Comment> comments;

    @ManyToMany(targetEntity = Tag.class)
    @JoinTable(
            name = "snippets_tags",
            joinColumns = @JoinColumn(name = "snippet_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    public Snippet(UUID id) {
        this.id = id;
    }

    public Snippet() {
    }

}
