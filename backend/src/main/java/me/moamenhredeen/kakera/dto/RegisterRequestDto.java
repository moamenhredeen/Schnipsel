package me.moamenhredeen.kakera.dto;

import me.moamenhredeen.kakera.entities.User;

public record RegisterRequestDto(
        String username,
        String email,
        String password) {
    public User toUser() {
        return new User(username, email, password);
    }

    public static RegisterRequestDto fromUser(User user) {
        return new RegisterRequestDto(user.getUsername(), user.getEmail(), user.getPassword());
    }
}
