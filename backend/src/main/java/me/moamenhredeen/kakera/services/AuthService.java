package me.moamenhredeen.kakera.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class AuthService {

    @PersistenceContext
    EntityManager em;
}
