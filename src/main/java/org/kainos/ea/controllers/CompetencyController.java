package org.kainos.ea.controllers;

import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.*;
import org.kainos.ea.models.BandCompetency;
import org.kainos.ea.models.BandRequest;
import org.kainos.ea.services.CompetencyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("All Tables Competency API")
@Path("/api")
public class CompetencyController {
    private final CompetencyService competencyService = new CompetencyService();

    @GET
    @Path("/competencies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetencies() {
        try {
            return Response.ok(competencyService.getAllCompetencies()).build();
        } catch (FailedToGetCompetenciesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/band-competencies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBandCompetency(BandCompetency bandCompetency) {
        try {
            return Response.status(Response.Status.CREATED).entity(competencyService.createBandCompetency(bandCompetency)).build();
        } catch (FailedToCreateBandCompetencyException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (InvalidBandCompetencyException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
