package com.megafair.auth.web;

import com.megafair.auth.exceptions.BaseServiceException;
import com.megafair.auth.exceptions.EntityNotFoundException;
import com.megafair.auth.exceptions.InvalidGameForPlatformException;
import com.megafair.auth.exceptions.InvalidPlatformForUserException;
import com.megafair.auth.web.dto.ErrorDro;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static com.megafair.auth.utils.Constants.GAME_IS_NOT_REGISTERED_FOR_THIS_PLATFORM;
import static com.megafair.auth.utils.Constants.USER_IS_NOT_REGISTERED_FROM_THIS_PLATFORM;
import static jakarta.ws.rs.core.Response.Status.FORBIDDEN;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<BaseServiceException> {

    @Override
    public Response toResponse(BaseServiceException e) {
        if (e instanceof InvalidGameForPlatformException) {
            return Response
                    .status(FORBIDDEN)
                    .entity(buildErrorEntity(GAME_IS_NOT_REGISTERED_FOR_THIS_PLATFORM))
                    .build();
        }

        if (e instanceof EntityNotFoundException) {
            return Response
                    .status(UNAUTHORIZED)
                    .entity(buildErrorEntity(((EntityNotFoundException) e).getErrorMessage()))
                    .build();
        }
        if (e instanceof InvalidPlatformForUserException) {
            return Response
                    .status(FORBIDDEN)
                    .entity(buildErrorEntity(USER_IS_NOT_REGISTERED_FROM_THIS_PLATFORM))
                    .build();
        }
        return Response.status(INTERNAL_SERVER_ERROR).build();
    }

    private ErrorDro buildErrorEntity(String message) {
        ErrorDro result = new ErrorDro();
        result.setMessage(message);
        return result;
    }
}
