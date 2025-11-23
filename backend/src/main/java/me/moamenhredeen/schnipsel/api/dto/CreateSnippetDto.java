package me.moamenhredeen.schnipsel.api.dto;

public record CreateSnippetDto(
        String title,
        String description,
        String content,
        String language
) {
}
