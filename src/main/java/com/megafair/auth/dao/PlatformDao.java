package com.megafair.auth.dao;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import com.megafair.auth.entity.PlatformGame;

@Singleton
public class PlatformDao {

    @Inject
    EntityManager entityManager;

    public PlatformGame findByPlatformIdAndGameId(Long platformId, Long gameId) {
        try {
            return (PlatformGame) entityManager.createQuery("select pg from PlatformGame pg where " +
                            "pg.platformId=?1 and pg.gameId = ?2")
                    .setParameter(1, platformId)
                    .setParameter(2, gameId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }    }


}
