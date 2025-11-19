package me.moamenhredeen.kakera.admin.controller;

import me.moamenhredeen.kakera.service.SnippetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/snippets")
public class AdminSnippetController {

    private final SnippetService snippetService;


    public AdminSnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping
    public String snippets(Model model) {
        var snippets = snippetService.getAllSnippets().toList();
        model.addAttribute("snippets", snippets);
        return "admin/snippet/list";
    }

    @GetMapping("{id}")
    public String snippetById(@PathVariable Long id, Model model) {
        var snippet = snippetService.getSnippetById(id);
        if (snippet.isEmpty()) {
            return "admin/not-found";
        }
        model.addAttribute("snippet", snippet.get());
        return "admin/snippet/details";
    }

}
