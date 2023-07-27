package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.CompetencyController;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Competency;
import org.kainos.ea.service.CompetencyService;
import org.mockito.Mockito;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CompetencyControllerUnitTest {
    CompetencyService competencyService = Mockito.mock(CompetencyService.class);
    CompetencyController competencyController = new CompetencyController(competencyService);

    Competency competency = new Competency(
            1,
            "Competency 1"
    );

    @Test
    void getAllCompetencies_shouldReturnOK_whenServiceReturnsList() throws FailedToGetCompetenciesException {
        List<Competency> sampleCompetencies = new ArrayList<>();
        sampleCompetencies.add(competency);
        sampleCompetencies.add(competency);
        sampleCompetencies.add(competency);

        Mockito.when(competencyService.getAllCompetencies()).thenReturn(sampleCompetencies);

        Response response = competencyController.getAllCompetencies();
        assert(response.getStatus() == 200);
    }

    @Test
    void getAllCompetencies_shouldReturnInternalServerError_whenServiceThrowsException() throws FailedToGetCompetenciesException {
        Mockito.when(competencyService.getAllCompetencies()).thenThrow(FailedToGetCompetenciesException.class);

        Response response = competencyController.getAllCompetencies();
        assert(response.getStatus() == 500);
    }

    @Test
    void getCompetency_shouldReturnBadRequest_whenServiceThrowCompetencyDoesNotExistException() throws FailedToGetCompetencyException, CompetencyDoesNotExistException {
        Mockito.when(competencyService.getCompetencyById(0)).thenThrow(CompetencyDoesNotExistException.class);
        assert(400 == competencyController.getCompetency(0).getStatus());
    }

    @Test
    void getCompetency_shouldReturnServerError_whenServiceThrowFailedToGetCompetencyException() throws FailedToGetCompetencyException, CompetencyDoesNotExistException {
        Mockito.when(competencyService.getCompetencyById(1)).thenThrow(FailedToGetCompetencyException.class);
        Response response = competencyController.getCompetency(1);
        System.out.println(response.getStatus());
        assert(500 == response.getStatus());
    }

    @Test
    void getCompetency_shouldReturnJobRole_whenServiceReturnsJobRole() throws FailedToGetCompetencyException, CompetencyDoesNotExistException {
        Mockito.when(competencyService.getCompetencyById(1)).thenReturn(competency);
        Response response= competencyController.getCompetency(1);
        assert(200 == response.getStatus());
    }
}
