package me.moamenhredeen.schnipsel.admin.dto;

import java.util.Optional;

public record UserFilter(
        Optional<Long> roleId,
        Optional<Boolean> enabled,
        Optional<String> search) {
}
