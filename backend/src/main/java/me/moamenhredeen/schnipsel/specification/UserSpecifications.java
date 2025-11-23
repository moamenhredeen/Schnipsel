package me.moamenhredeen.schnipsel.specification;

import me.moamenhredeen.schnipsel.admin.dto.UserFilter;
import me.moamenhredeen.schnipsel.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class UserSpecifications {

    public static Specification<User> byUsername(Optional<String> username) {
        return (root, _, cb) -> username.map(u -> cb.like(root.get("username"), u + "%")).orElse(null);
    }

    public static Specification<User> byEnabled(Optional<Boolean> enabled) {
        return (root, _, cb) -> enabled.map(e -> cb.equal(root.get("enabled"), e)).orElse(null);
    }
}
