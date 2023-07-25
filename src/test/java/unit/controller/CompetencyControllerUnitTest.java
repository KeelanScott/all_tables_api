package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.CompetencyController;
import org.kainos.ea.exception.FailedToGetCompetenciesException;
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
}
