package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.service.AuthService;
import org.kainos.ea.model.Login;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Login")
@Path("/api")
public class AuthController {
    private AuthService authService = new AuthService();

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        try {
            return Response.ok(authService.login(login)).build();
        } catch (FailedToLoginException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToGenerateTokenException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}



