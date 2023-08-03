package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.*;
import org.kainos.ea.service.CompetencyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Competencies")
@Path("/api")
public class CompetencyController {
    private final CompetencyService competencyService;

    public CompetencyController(CompetencyService competencyService) {
        this.competencyService = competencyService;
    }

    @GET
    @Path("/competencies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompetencies() {
        try {
            return Response.ok(competencyService.getAllCompetencies()).build();
        } catch (FailedToGetCompetenciesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/competencies/band/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBandCompetencies(@PathParam("id") int id) {
        try {
            return Response.ok(competencyService.getBandCompetencies(id)).build();
        } catch (FailedToGetBandCompetenciesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
