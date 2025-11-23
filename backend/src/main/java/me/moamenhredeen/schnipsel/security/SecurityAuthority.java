package me.moamenhredeen.schnipsel.security;

import me.moamenhredeen.schnipsel.model.Role;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public record SecurityAuthority(Role role) implements GrantedAuthority {

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_%s".formatted(this.role.getName());
    }
}
