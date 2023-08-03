package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.JobRoleDoesNotExistException;
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
            return Response.status(Response.Status.CREATED).entity(jobRoleService.createJobRole(jobRoleRequest)).build();
        } catch (DatabaseConnectionException | FailedToCreateJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (InvalidJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/job-roles/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJobRole(@PathParam("id") int id, JobRoleRequest jobRoleRequest) {
        try {
            return Response.ok(jobRoleService.updateJobRole(id, jobRoleRequest)).build();
        } catch (FailedToUpdateJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (InvalidJobRoleException | JobRoleDoesNotExistException e) {
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("id") int id) {
        try {
            return Response.ok(jobRoleService.deleteJobRole(id)).build();
        } catch (FailedToDeleteJobRoleException e) {
            System.err.println((e.getMessage()));
            return Response.serverError().build();
        } catch (JobRoleDoesNotExistException e) {
            System.err.println((e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
