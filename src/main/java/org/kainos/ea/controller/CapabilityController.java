package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.*;
import org.kainos.ea.service.CapabilityService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api("Capabilities")
@Path("/api")
public class CapabilityController {

    public CapabilityController(CapabilityService capabilityService) {
        this.capabilityService = capabilityService;
    }
    private final CapabilityService capabilityService;

    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilities() {
        try {
            return Response.ok(capabilityService.getAllCapabilities()).build();
        } catch (DatabaseConnectionException | FailedToGetCapabilityException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/capabilities/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilityById(@PathParam("id") int id) {
        try {
            return Response.ok(capabilityService.getCapabilityById(id)).build();
        } catch (DatabaseConnectionException | FailedToGetCapabilityException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (CapabilityDoesNotExistException e) {
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
