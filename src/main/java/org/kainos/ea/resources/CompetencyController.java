package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CompetencyService;
import org.kainos.ea.client.FailedToGetCompetenciesException;
import org.kainos.ea.client.FailedToGetCompetencyElementsException;
import org.kainos.ea.client.FailedToGetLevelsException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @GET
    @Path("/competencies/{competencyId}/elements")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetencyElements(@PathParam("competencyId") int competencyId) {
        try {
            return Response.ok(competencyService.getCompetencyElements(competencyId)).build();
        } catch (FailedToGetCompetencyElementsException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/levels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLevels() {
        try {
            return Response.ok(competencyService.getAllLevels()).build();
        } catch (FailedToGetLevelsException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
