
package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controllers.BandController;
import org.kainos.ea.exceptions.FailedToGetBandException;
import org.kainos.ea.models.Band;
import org.kainos.ea.services.BandService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandControllerUnitTest {
    BandService bandService = Mockito.mock(BandService.class);
    BandController bandController = new BandController(bandService);

    Band band = new Band(
            1,
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );



    @Test
    void getAllBands_shouldReturnOK_whenServiceReturnsList() throws FailedToGetBandException {

        List<Band> sampleBands = new ArrayList<>();
        sampleBands.add(band);
        sampleBands.add(band);
        sampleBands.add(band);

        Mockito.when(bandService.getAllBands()).thenReturn(sampleBands);

        Response response = bandController.getAllBands();
        assert(response.getStatus() == 200);
    }

    @Test
    void getAllBands_shouldReturnInternalServerError_whenServiceThrowsException() throws FailedToGetBandException {
        Mockito.when(bandService.getAllBands()).thenThrow(FailedToGetBandException.class);

        Response response = bandController.getAllBands();
        assert(response.getStatus() == 500);
    }

}
