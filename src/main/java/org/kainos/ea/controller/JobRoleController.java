package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.exception.FailedToGetJobRoleException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api("Job Roles")
@Path("/api")
public class JobRoleController {

    public JobRoleController(JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }
    private final JobRoleService jobRoleService;

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
}
