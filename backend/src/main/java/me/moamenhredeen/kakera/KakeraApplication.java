package me.moamenhredeen.kakera;

import me.moamenhredeen.kakera.security.NoPasswordAuthenticationProvider;
import me.moamenhredeen.kakera.security.RobotLoginConfigurer;
import me.moamenhredeen.kakera.security.UserDetailsServiceImpl;
import me.moamenhredeen.kakera.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
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
public class KakeraApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakeraApplication.class, args);
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {

        var robotLoginConfigurer = new RobotLoginConfigurer().password("beep-beep");

        return http
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/admin/home").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .authenticationProvider(new NoPasswordAuthenticationProvider().user("moamen"))
                .with(robotLoginConfigurer)
                .formLogin(t -> t.defaultSuccessUrl("/admin", true))
                //.oauth2Login(oauth -> oauth.defaultSuccessUrl("/admin", true))
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
}
