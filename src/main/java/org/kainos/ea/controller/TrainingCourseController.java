package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.FailedToGetTrainingCoursesException;
import org.kainos.ea.service.TrainingCourseService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Training Courses")
@Path("/api")
public class TrainingCourseController {
    private final TrainingCourseService trainingCourseService;

    public TrainingCourseController(TrainingCourseService trainingCourseService) {
        this.trainingCourseService = trainingCourseService;
    }

    @GET
    @Path("/training-courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTrainingCourses() {
        try {
            return Response.ok(trainingCourseService.getAllTrainingCourses()).build();
        } catch (FailedToGetTrainingCoursesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/training-courses/band/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainingForBand(@PathParam("id") int id) {
        try {
            return Response.ok(trainingCourseService.getTrainingForBand(id)).build();
        } catch (FailedToGetTrainingCoursesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}