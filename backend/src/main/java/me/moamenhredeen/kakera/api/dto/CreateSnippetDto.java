package me.moamenhredeen.kakera.api.dto;

public record CreateSnippetDto(
        String title,
        String description,
        String content,
        String language
) {
}
