package me.moamenhredeen.kakera.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.moamenhredeen.kakera.entities.User;

@ApplicationScoped
public class UserService {

    @PersistenceContext
    EntityManager em;

    public User createUser(User user) {
        em.persist(user);
        return user;
    }

    public  User updateUser(User user) {
        em.merge(user);
        return user;
    }

}
