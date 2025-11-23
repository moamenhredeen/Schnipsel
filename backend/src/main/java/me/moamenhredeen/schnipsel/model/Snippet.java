package me.moamenhredeen.schnipsel.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "snippets")
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String language;

    @OneToMany(mappedBy = "snippet")
    private Collection<Comment> comments;

    @ManyToOne
    private Profile profile;

    @Embedded
    private AuditMetadata auditMetadata;

    public Snippet() {}

    public Snippet(Long id, String title, String description, String content, String language) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.language = language;
    }

    public Snippet(String title, String description, String content, String language) {
        this(null, title, description, content, language);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Snippet snippet)) return false;
        return Objects.equals(id, snippet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
