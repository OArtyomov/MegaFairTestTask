package com.megafair.auth.dao;

import com.megafair.auth.entity.Platform;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Singleton
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlatformDao {

    @Inject
    EntityManager entityManager;

    public Platform finByIdentifier(String identifier) {
        try {
            return (Platform) entityManager.createQuery("select p from Platform p where " +
                            "p.identifier=?1")
                    .setParameter(1, identifier)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
