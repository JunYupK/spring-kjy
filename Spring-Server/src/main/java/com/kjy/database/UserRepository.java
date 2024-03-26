package com.kjy.database;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    public UserEntity save(UserEntity user){
        em.persist(user);
        return user;
    }
    public UserEntity findById(int id){
        return em.find(UserEntity.class, id);
    }
}
