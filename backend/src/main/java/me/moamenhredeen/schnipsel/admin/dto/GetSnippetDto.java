package me.moamenhredeen.schnipsel.admin.dto;

public record GetSnippetDto(
        Long id,
        String title,
        String description,
        String language
) {
}