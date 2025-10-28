package me.moamenhredeen.kakera.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq_gen")
    @SequenceGenerator(name = "tag_id_seq_gen", sequenceName = "tag_id_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(targetEntity = Snippet.class, mappedBy = "tags")
    private List<Snippet> snippets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }
}
