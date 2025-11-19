package me.moamenhredeen.kakera.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.ArrayList;
import java.util.List;

public class NoPasswordAuthenticationProvider implements AuthenticationProvider {

    private final List<String> allowedUsers = new ArrayList<>();


    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        if (allowedUsers.contains(name)) {
            return UsernamePasswordAuthenticationToken.authenticated(name, null, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public NoPasswordAuthenticationProvider user(String user) {
        allowedUsers.add(user);
        return this;
    }
}
