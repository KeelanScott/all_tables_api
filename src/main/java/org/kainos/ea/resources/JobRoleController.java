package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.client.FailedToGetJobRoleException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api("Job Roles")
@Path("/api")
public class JobRoleController {


    private JobRoleService jobRoleService = new JobRoleService();

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() {
        try {
            return Response.ok(jobRoleService.getAllJobRoles()).build();
        } catch (FailedToGetJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleById(@PathParam("id") int id) {
        try {
            return Response.ok(jobRoleService.getJobRolesById(id)).build();
        } catch (FailedToGetJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        }
    }

}