package com.megafair.auth.dao;


import com.megafair.auth.entity.User;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Singleton
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDao {

    @Inject
    EntityManager entityManager;


    public User findUser(String userIdentifier, String userSignature) {
        try {
            return (User) entityManager.createQuery("select u from User u where " +
                            "u.identifier=?1 and u.signature = ?2")
                    .setParameter(1, userIdentifier)
                    .setParameter(2, userSignature)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
