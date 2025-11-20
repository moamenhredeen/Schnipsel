package me.moamenhredeen.kakera.service;

import me.moamenhredeen.kakera.admin.dto.UserFilter;
import me.moamenhredeen.kakera.model.User;
import me.moamenhredeen.kakera.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static me.moamenhredeen.kakera.specification.UserSpecifications.byEnabled;
import static me.moamenhredeen.kakera.specification.UserSpecifications.byUsername;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAll(UserFilter filter, Pageable pageable){
        var spec = Specification
                .where(byUsername(filter.search()))
                .and(byEnabled(filter.enabled()));
        return this.userRepository.findAll(spec, pageable);
    }

    public Optional<User> getByUserName(String username){
        return this.userRepository.findByUsername(username);
    }

    public Optional<User> getById(Long id){
        return this.userRepository.findById(id);
    }

    public void register(User user) {
        user.setPassword("{noop}%s".formatted(user.getPassword()));
        this.userRepository.save(user);
    }

    public void update(User user) {
        user.setPassword("{noop}%s".formatted(user.getPassword()));
        this.userRepository.save(user);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
