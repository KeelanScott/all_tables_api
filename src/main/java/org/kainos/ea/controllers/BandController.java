package org.kainos.ea.controllers;

import io.swagger.annotations.Api;
import org.kainos.ea.services.BandService;
import org.kainos.ea.models.BandRequest;
import org.kainos.ea.exceptions.FailedToCreateBandException;
import org.kainos.ea.exceptions.InvalidBandException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("All Tables Band API")
@Path("/api")
public class BandController {
    private final BandService bandService = new BandService();

    @POST
    @Path("/bands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBand(BandRequest bandRequest) {
        try {
            return Response.status(Response.Status.CREATED).entity(bandService.createBand(bandRequest)).build();
        } catch (FailedToCreateBandException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidBandException e) {
            System.err.println(e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/bands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBands() {
        try {
            return Response.ok(bandService.getAllBands()).build();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
