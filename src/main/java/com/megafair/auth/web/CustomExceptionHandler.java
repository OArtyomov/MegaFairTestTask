package com.megafair.auth.web;

import com.megafair.auth.exceptions.BaseServiceException;
import com.megafair.auth.exceptions.EntityNotFoundException;
import com.megafair.auth.exceptions.InvalidGameForPlatformException;
import com.megafair.auth.exceptions.InvalidPlatformForUserException;
import com.megafair.auth.web.dto.ErrorDro;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<BaseServiceException> {

    @Override
    public Response toResponse(BaseServiceException e) {
        if (e instanceof InvalidGameForPlatformException) {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(buildErrorEntity("Game is not registered from this platform"))
                    .build();
        }

        if (e instanceof EntityNotFoundException) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(buildErrorEntity(((EntityNotFoundException) e).getErrorMessage()))
                    .build();
        }
        if (e instanceof InvalidPlatformForUserException) {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(buildErrorEntity("User is not registered from this platform"))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private ErrorDro buildErrorEntity(String message) {
        ErrorDro result = new ErrorDro();
        result.setMessage(message);
        return result;
    }
}
