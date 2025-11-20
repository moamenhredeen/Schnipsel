package me.moamenhredeen.kakera.admin.dto;

import me.moamenhredeen.kakera.model.Role;
import me.moamenhredeen.kakera.model.User;

import java.util.List;

public record GetUserDto(
        Long id,
        String username,
        String email,
        boolean enabled,
        List<String> roles
) {
    public static GetUserDto from(User user) {
        return new GetUserDto(user.getId(), user.getUsername(), user.getEmail(), user.isEnabled(), user.getRoles().stream().map(Role::getName).toList());
    }
}
