package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.FailedToGetBandException;
import org.kainos.ea.service.BandService;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.exception.InvalidBandException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("All Tables Band API")
@Path("/api")
public class BandController {
    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

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
        } catch (FailedToGetBandException e) {
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
        } catch (FailedToGetBandException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
