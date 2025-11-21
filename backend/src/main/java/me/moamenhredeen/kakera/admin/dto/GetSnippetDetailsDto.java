package me.moamenhredeen.kakera.admin.dto;

import java.util.Collection;

public record GetSnippetDetailsDto(
        Long id,
        String title,
        String description,
        String language,
        String content,
        Collection<GetCommentDto> comment
) {
}
