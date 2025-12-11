package me.moamenhredeen.schnipsel.admin.controller;

import me.moamenhredeen.schnipsel.admin.dto.GetCommentDto;
import me.moamenhredeen.schnipsel.admin.dto.GetSnippetDetailsDto;
import me.moamenhredeen.schnipsel.admin.dto.GetSnippetDto;
import me.moamenhredeen.schnipsel.admin.dto.SnippetFilter;
import me.moamenhredeen.schnipsel.model.Snippet;
import me.moamenhredeen.schnipsel.service.SnippetService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
@RequestMapping("/admin/snippets")
public class AdminSnippetController {

    private final SnippetService snippetService;


    public AdminSnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping
    public String snippets(
            @ModelAttribute("filter") SnippetFilter filter,
            Pageable pageable,
            Model model
    ) {
        var snippets = snippetService.getAllSnippets(filter, pageable).map(s ->
                new GetSnippetDto(
                        s.getId(),
                        s.getTitle(),
                        s.getDescription(),
                        s.getLanguage(),
                        ZonedDateTime
                                .ofInstant(s.getCreatedDate(), ZoneId.systemDefault())
                                .format(DateTimeFormatter.RFC_1123_DATE_TIME),
                        s.getLastModifiedDate()
                                .map(instant ->
                                    ZonedDateTime
                                        .ofInstant(instant, ZoneId.systemDefault())
                                        .format(DateTimeFormatter.RFC_1123_DATE_TIME))
                                .orElse(null),
                        s.getCreatedBy().orElse(null),
                        s.getLastModifiedBy().orElse(null)));
        model.addAttribute("snippets", snippets);
        model.addAttribute("filter", filter);
        return "admin/snippet/list";
    }

    @GetMapping("{id}")
    public String snippetById(@PathVariable Long id, Model model) {
        var snippet = snippetService.getSnippetById(id);
        if (snippet.isEmpty()) {
            return "admin/not-found";
        }
        model.addAttribute("snippet", snippet.map(s ->
                new GetSnippetDetailsDto(
                        s.getId(),
                        s.getTitle(),
                        s.getDescription(),
                        s.getLanguage(),
                        s.getContent(),
                        s.getCreatedBy().orElse(null),
                        s.getLastModifiedBy().orElse(null),
                        s.getComments().stream().map(c ->
                                new GetCommentDto(
                                        c.getId(),
                                        c.getContent()
                                )).toList())).get());
        return "admin/snippet/details";
    }


    @GetMapping("create")
    public String createUserForm(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "admin/snippet/create";
    }


    @PostMapping("create")
    public String createSnippet(Model model, Snippet snippet) {
        try {
            this.snippetService.create(snippet);
            return "redirect:/admin/snippets";
        } catch (Exception e) {
            model.addAttribute("snippet", new Snippet());
            model.addAttribute("error", e.getMessage());
            return "admin/snippet/create";
        }
    }


    @GetMapping("edit/{id}")
    public String editSnippetForm(@PathVariable Long id, Model model) {
        try {
            var snippet = this.snippetService.getById(id);
            model.addAttribute("snippet", snippet.orElseThrow());
            return "admin/snippet/edit";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/snippets";
        }
    }


    @PostMapping("edit")
    public String editSnippet(Snippet snippet, Model model) {
        try {
            this.snippetService.update(snippet);
            return "redirect:/admin/snippets";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/snippets/edit/%s".formatted(snippet.getId());
        }
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        this.snippetService.deleteById(id);
        return "redirect:/admin/snippets";
    }
}
