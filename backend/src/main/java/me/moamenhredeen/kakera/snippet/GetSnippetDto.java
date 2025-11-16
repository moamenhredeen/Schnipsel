package me.moamenhredeen.kakera.snippet;

public record GetSnippetDto(
        Long id,
        String title,
        String snippet,
        String description,
        String author
) {
}
