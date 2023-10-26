package com.megafair.auth.dao;

import com.megafair.auth.entity.Game;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Singleton
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameDao {


    @Inject
    EntityManager entityManager;

    private static final String FIND_GAME_BY_SYMBOL_QUERY =
            "select g from Game g where g.gameSymbol=?1";

    public Game findBySymbol(String symbol) {
        try {
            return (Game) entityManager.createQuery(FIND_GAME_BY_SYMBOL_QUERY)
                    .setParameter(1, symbol)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
