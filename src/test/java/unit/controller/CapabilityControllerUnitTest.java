package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.CapabilityController;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetCapabilityException;
import org.kainos.ea.exception.CapabilityDoesNotExistException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.service.CapabilityService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CapabilityControllerUnitTest {
    CapabilityService capabilityService =   Mockito.mock(CapabilityService.class);
    CapabilityController capabilityController = new CapabilityController(capabilityService);

    Capability capability = new Capability(
            1,
            "Software Engineering",
            "Software Engineering"
    );

    @Test
    void getCapabilities_shouldReturnOK_whenServiceReturnsList() throws FailedToGetCapabilityException, DatabaseConnectionException {
        List<Capability> sampleCapabilities = new ArrayList<>();
        sampleCapabilities.add(capability);
        sampleCapabilities.add(capability);
        sampleCapabilities.add(capability);

        Mockito.when(capabilityService.getAllCapabilities()).thenReturn(sampleCapabilities);

        Response response = capabilityController.getCapabilities();
        assert(response.getStatus() == 200);
    }

    @Test
    void getCapabilities_shouldReturnServerError_whenServiceThrowsFailedToGetCapabilityException() throws FailedToGetCapabilityException, DatabaseConnectionException {
        Mockito.when(capabilityService.getAllCapabilities()).thenThrow(FailedToGetCapabilityException.class);

        Response response = capabilityController.getCapabilities();
        assert(response.getStatus() == 500);
    }
}
