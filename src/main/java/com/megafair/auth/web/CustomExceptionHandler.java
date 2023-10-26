package com.megafair.auth.web;

import com.megafair.auth.exceptions.BaseServiceException;
import com.megafair.auth.exceptions.InvalidPlatformForUserException;
import com.megafair.auth.exceptions.UserNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.megafair.auth.exceptions.InvalidGameForPlatformException;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<BaseServiceException> {

    @Override
    public Response toResponse(BaseServiceException e) {
        if (e instanceof InvalidGameForPlatformException) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        if (e instanceof UserNotFoundException) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (e instanceof InvalidPlatformForUserException) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
