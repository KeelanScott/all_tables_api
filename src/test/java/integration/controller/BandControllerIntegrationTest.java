package integration.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandControllerIntegrationTest {

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getBands_shouldReturnList() {
        List<Band> response = APP.client().target("http://localhost:8080/api/bands")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void createBand_shouldReturnId(){
        int response = APP.client().target("http://localhost:8080/api/bands")
                .request()
                .post(Entity.json(new BandRequest("Test Band", "Level", "responsibilities")), Response.class)
                .readEntity(Integer.class);
        Assertions.assertNotNull(response);
    }

    @Test
    void getBand_shouldReturnBand(){
        Band response = APP.client().target("http://localhost:8080/api/bands/1")
                .request()
                .get(Band.class);
        Assertions.assertNotNull(response);
    }
}
