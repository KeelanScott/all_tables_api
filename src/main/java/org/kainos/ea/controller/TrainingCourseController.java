package org.kainos.ea.controller;

import io.swagger.annotations.Api;
import org.kainos.ea.exception.FailedToCreateBandTrainingCourseException;
import org.kainos.ea.exception.FailedToGetTrainingCoursesException;
import org.kainos.ea.model.BandTrainingCourse;
import org.kainos.ea.service.TrainingCourseService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("All Tables Training API")
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

    @POST
    @Path("/band-training-courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBand(BandTrainingCourse bandTrainingCourse) {
        try {
            return Response.status(Response.Status.CREATED).entity(trainingCourseService.createBandTrainingCourse(bandTrainingCourse)).build();
        } catch (FailedToCreateBandTrainingCourseException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

}
