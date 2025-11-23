package me.moamenhredeen.schnipsel.api.controller;


import me.moamenhredeen.schnipsel.admin.dto.SnippetFilter;
import me.moamenhredeen.schnipsel.api.dto.CreateCommentDto;
import me.moamenhredeen.schnipsel.api.dto.CreateSnippetDto;
import me.moamenhredeen.schnipsel.api.dto.GetCommentDto;
import me.moamenhredeen.schnipsel.api.dto.GetSnippetDto;
import me.moamenhredeen.schnipsel.model.Snippet;
import me.moamenhredeen.schnipsel.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    private final SnippetService snippetService;

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping
    public List<GetSnippetDto> getSnippet() {
        return List.of();
    }

    @GetMapping("{id}/comments")
    public List<GetCommentDto> getSnippetComments(@PathVariable Long id) {
        return this.snippetService.getSnippetComments(id)
                .map(c -> new GetCommentDto(c.getId(), c.getContent()))
                .toList();
    }

    @GetMapping("{id}")
    public Optional<GetSnippetDto> getSnippet(@PathVariable Long id) {
        return this.snippetService.getSnippetById(id).map(s ->
                new GetSnippetDto(s.getId(), s.getTitle(), s.getDescription(), s.getContent(), s.getLanguage()));
    }

    @PostMapping
    public GetSnippetDto createSnippet(@RequestBody CreateSnippetDto dto) {
        var created =  snippetService.createSnippet(
                new Snippet(dto.title(), dto.description(), dto.content(), dto.language()));
        return new GetSnippetDto(created.getId(), created.getTitle(), created.getDescription(), created.getContent(), created.getLanguage());
    }

    @PostMapping("/{id}/comments")
    public GetCommentDto commentSnippet(@PathVariable Long id, @RequestBody CreateCommentDto dto) {
        var created = snippetService.commentSnippet(id, dto.content());
        return new GetCommentDto(created.getId(), created.getContent());
    }

    @PutMapping("/{id}")
    public GetSnippetDto putSnippet(@PathVariable Long id, @RequestBody CreateSnippetDto dto) {
        var updated = snippetService.updateSnippet(new Snippet(id, dto.title(), dto.description(), dto.content(), dto.language()));
        return new GetSnippetDto(updated.getId(), updated.getTitle(), updated.getDescription(), updated.getContent(), updated.getLanguage());
    }

    @DeleteMapping("/{id}")
    public void deleteSnippet(@PathVariable Long id) {
        snippetService.deleteSnippet(id);
    }
}
