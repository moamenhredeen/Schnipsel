package me.moamenhredeen.kakera.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq_gen")
    @SequenceGenerator(name = "comment_id_seq_gen", sequenceName = "comment_id_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne()
    private User author;

    @ManyToOne()
    private Snippet snippet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
