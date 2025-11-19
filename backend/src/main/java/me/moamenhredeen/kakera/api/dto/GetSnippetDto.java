package me.moamenhredeen.kakera.api.dto;

public record GetSnippetDto(
        Long id,
        String title,
        String description,
        String content,
        String language
) {
}
