package me.moamenhredeen.schnipsel.admin.dto;

import me.moamenhredeen.schnipsel.model.Operator;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record GetSnippetDto(
        Long id,
        String title,
        String description,
        String language,
        String createdDate,
        String lastModifiedDate,
        Operator createdBy,
        Operator lastModifiedBy
) {
}