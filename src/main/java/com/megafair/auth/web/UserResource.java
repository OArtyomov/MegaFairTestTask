package com.megafair.auth.web;

import com.megafair.auth.service.UserService;
import com.megafair.auth.web.dto.AuthenticationResult;
import com.megafair.auth.web.dto.LoginUserDTO;
import com.megafair.auth.web.dto.RegisterResult;
import com.megafair.auth.web.dto.RegisterUserDTO;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@Singleton
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Path("login")
    @PermitAll
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public AuthenticationResult login(@Valid LoginUserDTO loginUserDTO) {
        String token = userService.validateUser(loginUserDTO.getPlatformIdentifier(),
                loginUserDTO.getGameSymbol(),
                loginUserDTO.getUserIdentifier(), loginUserDTO.getUserSignature());
        AuthenticationResult result = new AuthenticationResult();
        result.setToken(token);
        result.setTokenType("Bearer");
        return result;
    }

    @POST
    @Path("register")
    @PermitAll
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public RegisterResult register(@Valid RegisterUserDTO registerUserDTO) {
        return userService.registerUser(registerUserDTO.getUserIdentifier(),
                registerUserDTO.getPlatformIdentifier());
    }
}
