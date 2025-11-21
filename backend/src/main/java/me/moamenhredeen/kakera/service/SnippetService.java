package me.moamenhredeen.kakera.service;

import me.moamenhredeen.kakera.admin.dto.SnippetFilter;
import me.moamenhredeen.kakera.model.Comment;
import me.moamenhredeen.kakera.model.Snippet;
import me.moamenhredeen.kakera.repository.CommentRepository;
import me.moamenhredeen.kakera.repository.SnippetRepository;
import me.moamenhredeen.kakera.specification.SnippetSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SnippetService {

    private final SnippetRepository snippetRepository;
    private final CommentRepository commentRepository;

    public SnippetService(SnippetRepository snippetRepository, CommentRepository commentRepository) {
        this.snippetRepository = snippetRepository;
        this.commentRepository = commentRepository;
    }

    public Page<Snippet> getAllSnippets(SnippetFilter filter, Pageable pageable) {
        return snippetRepository.findAll(
                Specification
                    .where(SnippetSpecification.byTitle(filter.title())),
                pageable
        );
    }

    public Optional<Snippet> getSnippetById(Long id) {
        return snippetRepository.findByIdWithComments(id);
    }

    public Snippet createSnippet(Snippet snippet) {
        return snippetRepository.save(snippet);
    }

    public Snippet updateSnippet(Snippet snippet) {
        if (snippet.getId() == null) {
            throw new IllegalArgumentException("Snippet id must not be null");
        }
        return snippetRepository.save(snippet);
    }

    public void deleteSnippet(Long id) {
        snippetRepository.deleteById(id);
    }

    public Page<Comment> getSnippetComments(Long id) {
        return commentRepository.findBySnippetId(id, Pageable.ofSize(10));
    }

    public Comment commentSnippet(Long id, String content) {
        var comment = new Comment();
        comment.setContent(content);
        var snippet = new Snippet();
        snippet.setId(id);
        comment.setSnippet(snippet);
        return this.commentRepository.save(comment);
    }

    public void create(Snippet snippet) {
        this.snippetRepository.save(snippet);
    }

    public Optional<Snippet> getById(Long id) {
        return this.snippetRepository.findById(id);
    }

    public void update(Snippet snippet) {
        this.snippetRepository.save(snippet);
    }

    public void deleteById(Long id) {
        this.snippetRepository.deleteById(id);
    }
}
