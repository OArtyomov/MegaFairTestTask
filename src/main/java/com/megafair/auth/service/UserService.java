package com.megafair.auth.service;

import com.megafair.auth.dao.GameDao;
import com.megafair.auth.dao.PlatformDao;
import com.megafair.auth.dao.PlatformGameDao;
import com.megafair.auth.dao.UserDao;
import com.megafair.auth.entity.Game;
import com.megafair.auth.entity.Platform;
import com.megafair.auth.entity.PlatformGame;
import com.megafair.auth.entity.User;
import com.megafair.auth.exceptions.InvalidGameForPlatformException;
import com.megafair.auth.exceptions.InvalidPlatformForUserException;
import com.megafair.auth.exceptions.UserNotFoundException;
import com.megafair.auth.web.dto.RegisterResult;
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
    PlatformGameDao platformGameDao;

    @Inject
    PlatformDao platformDao;

    @Inject
    SignatureService signatureService;

    @Inject
    GameDao gameDao;

    private static final Set<String> DEFAULT_USER_ROLE = singleton(USER_ROLE);


    @Transactional
    public String validateUser(String platformIdentifier,
                               String gameSymbol,
                               String userIdentifier,
                               String userSignature) {
        //This logic can be devi
        User user = userDao.findUser(userIdentifier, userSignature);
        if (user == null) {
            throw new UserNotFoundException();
        }

        Platform platform = platformDao.finByIdentifier(platformIdentifier);
        if (platform == null) {
            throw new InvalidPlatformForUserException();

        }
        Long platformId = platform.getId();
        if (!user.getPlatformId().equals(platformId)) {
            throw new InvalidPlatformForUserException();
        }

        Game game = gameDao.findBySymbol(gameSymbol);
        if (game == null) {
            throw new InvalidGameForPlatformException();
        }

        PlatformGame platformGame = platformGameDao.findByPlatformIdAndGameId(platformId, game.getId());
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

    @Transactional
    public RegisterResult registerUser(String userIdentifier, String platformIdentifier) {
        Platform platform = platformDao.finByIdentifier(platformIdentifier);
        if (platform == null) {
            throw new InvalidPlatformForUserException();
        }
        User user = new User();
        user.setIdentifier(userIdentifier);
        user.setPlatformId(platform.getId());
        String signature = signatureService.generateRandomSignature();
        user.setSignature(signature);
        userDao.saveUser(user);
        RegisterResult result = new RegisterResult();
        result.setUserIdentifier(userIdentifier);
        result.setUserSignature(signature);
        return result;
    }
}
