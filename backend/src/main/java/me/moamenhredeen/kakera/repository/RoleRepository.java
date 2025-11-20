package me.moamenhredeen.kakera.repository;

import me.moamenhredeen.kakera.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name like ?1%")
    Page<Role> findByName(String name, Pageable pageable);
}
