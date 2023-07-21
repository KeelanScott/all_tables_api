package org.kainos.ea.controllers;

import io.swagger.annotations.Api;
import org.kainos.ea.exceptions.FailedToGetTrainingCoursesException;
import org.kainos.ea.services.TrainingCourseService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("All Tables Training API")
@Path("/api")
public class TrainingCourseController {
    private final TrainingCourseService trainingCourseService = new TrainingCourseService();

    @GET
    @Path("/training-courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrainingCourses() {
        try {
            return Response.ok(trainingCourseService.getAllTrainingCourses()).build();
        } catch (FailedToGetTrainingCoursesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

}
