package org.kainos.ea.controller;
import io.swagger.annotations.Api;
import org.kainos.ea.exception.DatabaseConnectionException;
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
    public AuthController(AuthService authService){
        this.authService= authService;
    }
    private final AuthService authService;
    String token = "";
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        try {
            token = authService.login(login);
        } catch (FailedToLoginException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToGenerateTokenException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.OK).entity(token).build();
    }
}