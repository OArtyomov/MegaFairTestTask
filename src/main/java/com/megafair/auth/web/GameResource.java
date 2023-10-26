package com.megafair.auth.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Singleton;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.security.Principal;

import static com.megafair.auth.utils.Constants.GAME_ID_CLAIM_NAME;
import static com.megafair.auth.utils.Constants.PLATFORM_ID_CLAIM_NAME;
import static com.megafair.auth.utils.Constants.USER_ID_CLAIM_NAME;
import static com.megafair.auth.utils.Constants.USER_ROLE;

@Path("/game")
@Singleton
public class GameResource {

    @POST
    @Path("makeBet")
    @RolesAllowed({USER_ROLE})
    @Produces(MediaType.TEXT_PLAIN)
    public String makeBet(@Context SecurityContext ctx) {
        JsonWebToken jwt = (JsonWebToken) ctx.getUserPrincipal();
        String info = "User: " + jwt.getClaim(USER_ID_CLAIM_NAME) +
                ", platform_id: " + jwt.getClaim(PLATFORM_ID_CLAIM_NAME) +
                ", game_id: " + jwt.getClaim(GAME_ID_CLAIM_NAME);
        return info;
    }


}
