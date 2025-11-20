package me.moamenhredeen.kakera.service;

import me.moamenhredeen.kakera.model.Role;
import me.moamenhredeen.kakera.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Page<Role> getAllRoles(String searchText) {
        if (searchText != null && !searchText.isEmpty()) {
            return roleRepository.findByName(searchText, Pageable.ofSize(10));
        }
        return roleRepository.findAll(Pageable.ofSize(10));
    }

    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

    public Role  create(Role role) {
        return this.roleRepository.save(role);
    }

    public void deleteById(Long id) {
        this.roleRepository.deleteById(id);
    }
}
