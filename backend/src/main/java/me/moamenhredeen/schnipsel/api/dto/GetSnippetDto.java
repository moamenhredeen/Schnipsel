package me.moamenhredeen.schnipsel.api.dto;

public record GetSnippetDto(
        Long id,
        String title,
        String description,
        String content,
        String language
) {
}
