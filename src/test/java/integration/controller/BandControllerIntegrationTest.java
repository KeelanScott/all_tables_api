package integration.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.BandRequest;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandControllerIntegrationTest {

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void createBand_shouldReturnId(){
        int response = APP.client().target("http://localhost:8080/api/bands")
                .request()
                .post(Entity.json(new BandRequest("Test Band", "Level", "responsibilities")), Response.class)
                .readEntity(Integer.class);
        Assertions.assertNotNull(response);
    }
}
