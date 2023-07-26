package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.BandRequest;
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

    @Test
    void createBand_shouldReturnOK_whenServiceReturnsId() throws InvalidBandException, FailedToCreateBandException {
        Mockito.when(bandService.createBand(bandRequest)).thenReturn(1);

        Response response = bandController.createBand(bandRequest);
        assert(response.getStatus() == 201);
    }
}
