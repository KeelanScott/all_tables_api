package integration.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.Capability;

import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CapabilityControllerIntegrationTest {

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getCapabilities_shouldReturnList() {
        List<Capability> response = APP.client().target("http://localhost:8080/api/capabilities")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getCapabilityById_shouldReturnCapability() {
        Capability response = APP.client().target("http://localhost:8080/api/capabilities/1")
                .request()
                .get(Capability.class);

        Assertions.assertEquals(1, response.getId());
    }

    @Test
    void getCapabilityById_shouldReturn404_whenIDNotFound() {
        Response responseCapability = APP.client().target("http://localhost:8080/api/capabilities/" + 0)
                .request()
                .get(Response.class);

        Assertions.assertEquals(404, responseCapability.getStatus());
    }
}
