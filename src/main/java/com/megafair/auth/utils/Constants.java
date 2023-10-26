package com.megafair.auth.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String GAME_ID_CLAIM_NAME = "game_id";

    public static final String PLATFORM_ID_CLAIM_NAME = "platform_id";

    public static final String USER_ID_CLAIM_NAME = "user_id";

    public static final String USER_ROLE = "User";

    //TODO: Should be moved for properties for internationalization
    public static final String GAME_IS_NOT_REGISTERED_FOR_THIS_PLATFORM = "Game is not registered for this platform";
    public static final String USER_IS_NOT_REGISTERED_FROM_THIS_PLATFORM = "User is not registered from this platform";
    public static final String GAME_IS_NOT_FOUND = "Game is not found";
    public static final String PLATFORM_IS_NOT_FOUND = "Platform is not found";
    public static final String USER_IS_NOT_FOUND = "User is not found";
}
