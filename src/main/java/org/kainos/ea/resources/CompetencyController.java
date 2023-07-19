package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CompetencyService;
import org.kainos.ea.client.FailedToGetCompetenciesException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
