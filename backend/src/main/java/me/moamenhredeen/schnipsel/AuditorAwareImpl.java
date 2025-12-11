package me.moamenhredeen.schnipsel;

import me.moamenhredeen.schnipsel.model.Operator;
import me.moamenhredeen.schnipsel.security.SecurityUser;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<Operator> {

    @Override
    public @NonNull Optional<Operator> getCurrentAuditor() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return Optional.empty();
        var securityUser = (SecurityUser) auth.getPrincipal();
        if(securityUser == null) return Optional.empty();
        var operator = new Operator();
        operator.setId(securityUser.user().getId());
        return Optional.of(operator);
    }
}
