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

    private static final String FIND_PLATFORM_BY_IDENTIFIER_QUERY =
            "select p from Platform p where p.identifier=?1";
    @Inject
    EntityManager entityManager;

    public Platform finByIdentifier(String identifier) {
        try {
            return (Platform) entityManager.createQuery(FIND_PLATFORM_BY_IDENTIFIER_QUERY)
                    .setParameter(1, identifier)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
