package me.moamenhredeen.kakera.dto;

import me.moamenhredeen.kakera.entities.User;

public record LoginRequestDto(
        String email,
        String password) {
}
