package me.moamenhredeen.schnipsel;

import me.moamenhredeen.schnipsel.model.Operator;
import me.moamenhredeen.schnipsel.security.UserDetailsServiceImpl;
import me.moamenhredeen.schnipsel.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableJpaAuditing
public class SchnipselApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchnipselApplication.class, args);
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .authorizeHttpRequests(authZ -> authZ
                            .requestMatchers("/*.css", "/*.woff2", "/login*", "/error*", "/logout*").permitAll()
                            .requestMatchers("/admin/users*", "/admin/roles*").hasRole("superadmin")
                            .requestMatchers("/admin*").hasAnyRole("admin", "superadmin")
                            .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/admin", true))
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    UserDetailsService userDetailsService(UserService userService){
        return new UserDetailsServiceImpl(userService);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessListener(){
        return event -> IO.println("\uD83C\uDF89 Authentication Success, %s".formatted(event.getAuthentication().getName()));
    }

    @Bean
    public AuditorAware<Operator> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
