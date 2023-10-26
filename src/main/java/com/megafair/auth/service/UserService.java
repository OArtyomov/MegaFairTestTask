package com.megafair.auth.service;

import com.megafair.auth.dao.PlatformDao;
import com.megafair.auth.dao.UserDao;
import com.megafair.auth.entity.PlatformGame;
import com.megafair.auth.entity.User;
import com.megafair.auth.exceptions.InvalidGameForPlatformException;
import com.megafair.auth.exceptions.InvalidPlatformForUserException;
import com.megafair.auth.exceptions.UserNotFoundException;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Set;

import static com.megafair.auth.utils.Constants.GAME_ID_CLAIM_NAME;
import static com.megafair.auth.utils.Constants.PLATFORM_ID_CLAIM_NAME;
import static com.megafair.auth.utils.Constants.USER_ID_CLAIM_NAME;
import static com.megafair.auth.utils.Constants.USER_ROLE;
import static java.util.Collections.singleton;

@Singleton
public class UserService {


    @Inject
    UserDao userDao;

    @Inject
    PlatformDao platformDao;

    private static final Set<String> DEFAULT_USER_ROLE = singleton(USER_ROLE);


    @Transactional
    public String validateUser(Long platformId,
                               Long gameId,
                               String userIdentifier,
                               String userSignature) {
        //This logic can be devi
        User user = userDao.findUser(userIdentifier, userSignature);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!user.getPlatformId().equals(platformId)) {
            throw new InvalidPlatformForUserException();
        }
        PlatformGame platformGame = platformDao.findByPlatformIdAndGameId(platformId, gameId);
        if (platformGame == null || !platformGame.getEnabled()) {
            throw new InvalidGameForPlatformException();
        }
        return Jwt.issuer("https://example.com/issuer")
                .upn(user.getIdentifier())
                .groups(DEFAULT_USER_ROLE)
                .claim(PLATFORM_ID_CLAIM_NAME, platformGame.getPlatformId())
                .claim(GAME_ID_CLAIM_NAME, platformGame.getGameId())
                .claim(USER_ID_CLAIM_NAME, user.getId().toString())
                .sign();
    }
}
