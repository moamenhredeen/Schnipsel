package me.moamenhredeen.schnipsel.admin.dto;

import me.moamenhredeen.schnipsel.model.Operator;

import java.util.Collection;

public record GetSnippetDetailsDto(
        Long id,
        String title,
        String description,
        String language,
        String content,
        Operator createdBy,
        Operator lastModifiedBy,
        Collection<GetCommentDto> comment
) {
}
