package me.moamenhredeen.kakera.service;

import me.moamenhredeen.kakera.model.User;
import me.moamenhredeen.kakera.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAllUsers(){
        return this.userRepository.findAll(Pageable.ofSize(10));
    }

    public Page<User> search(String searchText) {
        return this.userRepository.search(searchText, Pageable.ofSize(10));
    }

    public Optional<User> getById(Long id){
        return this.userRepository.findById(id);
    }

    public Optional<User> getByUserName(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void register(User user) {
        user.setPassword("{noop}%s".formatted(user.getPassword()));
        this.userRepository.save(user);
    }

    public void update(User user) {
        this.userRepository.save(user);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
