package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.BandDoesNotExistException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetBandException;
import org.kainos.ea.service.BandService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Bands")
@Path("/api")
public class BandController {
    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GET
    @Path("/bands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBands() {
        try {
            return Response.ok(bandService.getAllBands()).build();
        } catch (FailedToGetBandException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/bands/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandById(@PathParam("id") int id) {
        try {
            return Response.ok(bandService.getBandById(id)).build();
        } catch (FailedToGetBandException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (BandDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
