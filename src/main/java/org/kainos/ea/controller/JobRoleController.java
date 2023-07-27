package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.service.JobRoleService;

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
        } catch (DatabaseConnectionException | FailedToGetJobRoleException e) {
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
        } catch (DatabaseConnectionException | FailedToGetJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (JobRoleDoesNotExistException e) {
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/job-roles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(JobRoleRequest jobRoleRequest) {
        try {
            jobRoleService.createJobRole(jobRoleRequest);
            return Response.status(Response.Status.CREATED).build();
        } catch (DatabaseConnectionException | FailedToCreateJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (InvalidJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
