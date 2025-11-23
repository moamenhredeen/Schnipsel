package me.moamenhredeen.schnipsel.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public class RobotAuthentication implements Authentication {
    private final boolean authenticated;
    private final String password;
    private List<? extends GrantedAuthority> authorities;

    public RobotAuthentication(boolean authenticated, String password,  List<? extends GrantedAuthority> authorities) {
        this.authenticated = authenticated;
        this.password = password;
        this.authorities = authorities;
    }

    public static Authentication authenticated(List<? extends GrantedAuthority> authorities) {
        return new RobotAuthentication(true, null,  authorities);
    }

    public static Authentication unauthenticated(String password) {
        return new RobotAuthentication(false, password, null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_ROBOT");
    }

    @Override
    public @Nullable Object getCredentials() {
        return null;
    }

    @Override
    public @Nullable Object getDetails() {
        return null;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return getName();
    }

    @Override
    public String getName() {
        return "Robot";
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("do not set authenticated");
    }

    public String getPassword() {
        return password;
    }
}
