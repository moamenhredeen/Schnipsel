package me.moamenhredeen.kakera.admin.dto;

public record GetSnippetDto(
        Long id,
        String title,
        String description,
        String language
) {
}