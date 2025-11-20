package me.moamenhredeen.kakera.admin.dto;

public record GetSnippetDetailsDto(
        Long id,
        String title,
        String description,
        String language,
        String content
) {
}
