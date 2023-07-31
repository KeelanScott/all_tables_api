package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandCompetencyRequest;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.model.BandWithDetailsRequest;
import org.kainos.ea.service.BandService;
import org.mockito.Mockito;
import javax.ws.rs.core.Response;
@ExtendWith(DropwizardExtensionsSupport.class)
public class BandControllerUnitTest {
    BandService bandService = Mockito.mock(BandService.class);
    BandController bandController = new BandController(bandService);

    BandRequest bandRequest = new BandRequest(
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );

    BandCompetencyRequest competency = new BandCompetencyRequest(
            1,
            "Description"
    );

    BandCompetencyRequest[] competencies = { competency, competency };
    int[] trainingCourses = { 1, 1 };

    BandWithDetailsRequest bandWithDetailsRequest = new BandWithDetailsRequest(bandRequest, competencies, trainingCourses);

    @Test
    void createBand_shouldReturnOK_whenServiceReturnsId() throws InvalidBandException, FailedToCreateBandException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandService.createBand(bandWithDetailsRequest)).thenReturn(1);
        Response response = bandController.createBand(bandWithDetailsRequest);
        assert(response.getStatus() == 201);
    }

    @Test
    void createBand_shouldReturnServerError_whenServiceThrowsFailedToCreateBandException() throws InvalidBandException, FailedToCreateBandException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandService.createBand(bandWithDetailsRequest)).thenThrow(FailedToCreateBandException.class);
        Response response = bandController.createBand(bandWithDetailsRequest);
        assert(response.getStatus() == 500);
    }

    @Test
    void createBand_shouldReturnServerError_whenServiceThrowsFailedToCreateBandCompetencyException() throws InvalidBandException, FailedToCreateBandException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandService.createBand(bandWithDetailsRequest)).thenThrow(FailedToCreateBandCompetencyException.class);
        Response response = bandController.createBand(bandWithDetailsRequest);
        assert(response.getStatus() == 500);
    }

    @Test
    void createBand_shouldReturnServerError_whenServiceThrowsFailedToCreateBandTrainingCourseException() throws InvalidBandException, FailedToCreateBandException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandService.createBand(bandWithDetailsRequest)).thenThrow(FailedToCreateBandTrainingCourseException.class);
        Response response = bandController.createBand(bandWithDetailsRequest);
        assert(response.getStatus() == 500);
    }

    @Test
    void createBand_shouldReturnBadRequest_whenServiceThrowsInvalidBandException() throws InvalidBandException, FailedToCreateBandException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandService.createBand(bandWithDetailsRequest)).thenThrow(InvalidBandException.class);
        Response response = bandController.createBand(bandWithDetailsRequest);
        assert(response.getStatus() == 400);
    }

    @Test
    void createBand_shouldReturnBadRequest_whenServiceThrowsInvalidBandCompetencyException() throws InvalidBandException, FailedToCreateBandException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandService.createBand(bandWithDetailsRequest)).thenThrow(InvalidBandCompetencyException.class);
        Response response = bandController.createBand(bandWithDetailsRequest);
        assert(response.getStatus() == 400);
    }
}
