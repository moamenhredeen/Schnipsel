package me.moamenhredeen.schnipsel.specification;

import me.moamenhredeen.schnipsel.model.Snippet;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class SnippetSpecification {

    public static Specification<Snippet> byTitle(Optional<String> title) {
        return (root, _, cb) -> title.map(t -> cb.like(root.get("title"), t + "%")).orElse(null);
    }

}
