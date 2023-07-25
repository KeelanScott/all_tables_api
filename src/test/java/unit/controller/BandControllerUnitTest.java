
package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.exception.FailedToGetBandException;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.service.BandService;
import org.mockito.Mockito;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandControllerUnitTest {
    BandService bandService = Mockito.mock(BandService.class);
    BandController bandController = new BandController(bandService);

    BandRequest bandRequest = new BandRequest(
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );

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

    @Test
    void createBand_shouldReturnOK_whenServiceReturnsId() throws InvalidBandException, FailedToCreateBandException {
        Mockito.when(bandService.createBand(bandRequest)).thenReturn(1);

        Response response = bandController.createBand(bandRequest);
        assert(response.getStatus() == 201);
    }

    @Test
    void getBandById_shouldReturnBand_whenBandExists() throws FailedToGetBandException {
        Mockito.when(bandService.getBandById(1)).thenReturn(band);

        Response response = bandController.getBandById(1);
        assert(response.getStatus() == 200);
    }
}
