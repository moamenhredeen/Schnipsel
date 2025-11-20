package me.moamenhredeen.kakera.security;

import me.moamenhredeen.kakera.model.Role;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public record SecurityAuthority(Role role) implements GrantedAuthority {

    @Override
    public @Nullable String getAuthority() {
        return this.role.getName();
    }
}
