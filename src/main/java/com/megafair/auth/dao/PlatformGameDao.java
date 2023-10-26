package com.megafair.auth.dao;

import com.megafair.auth.entity.PlatformGame;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Singleton
public class PlatformGameDao {

    public static final String FIND_PLATFORM_GAME_BY_PLATFORM_ID_AND_GAME_ID =
            "select pg from PlatformGame pg where pg.platformId=?1 and pg.gameId = ?2";
    @Inject
    EntityManager entityManager;

    public PlatformGame findByPlatformIdAndGameId(Long platformId, Long gameId) {
        try {
            return (PlatformGame) entityManager.createQuery(FIND_PLATFORM_GAME_BY_PLATFORM_ID_AND_GAME_ID)
                    .setParameter(1, platformId)
                    .setParameter(2, gameId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
