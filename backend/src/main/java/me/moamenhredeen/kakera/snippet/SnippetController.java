package me.moamenhredeen.kakera.snippet;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    @GetMapping
    public List<GetSnippetDto> getSnippet() {
        return List.of(
                new GetSnippetDto(1L, "title", "snippet", "description", "author"),
                new GetSnippetDto(2L, "title2", "snippet2", "description2", "author2")
        );
    }
}
