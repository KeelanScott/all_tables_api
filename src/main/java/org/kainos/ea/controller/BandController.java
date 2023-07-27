package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.BandWithDetailsRequest;
import org.kainos.ea.service.BandService;
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
    public Response createBand(BandWithDetailsRequest bandWithDetailsRequest) {
        try {
            return Response.status(Response.Status.CREATED).entity(bandService.createBand(bandWithDetailsRequest)).build();
        } catch (FailedToCreateBandException | FailedToCreateBandCompetencyException |
                 FailedToCreateBandTrainingCourseException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidBandException | InvalidBandCompetencyException e) {
            System.err.println(e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
