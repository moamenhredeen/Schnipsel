package me.moamenhredeen.kakera.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "snippets")
@NamedQuery(name = "findSnippets", query = "select s from Snippet s")
@NamedQuery(name = "findSnippetById", query = "select s from Snippet s where s.id = :id")
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "snippet_id_seq_gen")
    @SequenceGenerator(name = "snippet_id_seq_gen", sequenceName = "snippet_id_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
