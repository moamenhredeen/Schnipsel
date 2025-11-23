package me.moamenhredeen.schnipsel.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

public class RobotLoginConfigurer extends AbstractHttpConfigurer<RobotLoginConfigurer, HttpSecurity> {

    private final List<String> passwords = new ArrayList<>();

    @Override
    public void init(HttpSecurity http) {
        http.authenticationProvider(new RobotAuthenticationProvider(passwords));
    }

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager manager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterBefore(new RobotFilter(manager), UsernamePasswordAuthenticationFilter.class);
    }

    public RobotLoginConfigurer password(String password) {
        passwords.add(password);
        return this;
    }
}
