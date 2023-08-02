package integration.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandControllerIntegrationTest {
    BandRequest bandRequest = new BandRequest(
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );

    BandCompetencyRequest competency = new BandCompetencyRequest(
            1,
            "Description"
    );

    BandCompetencyRequest[] competencies = { competency };
    int[] trainingCourses = { 1, 2 };

    BandWithDetailsRequest bandWithDetailsRequest = new BandWithDetailsRequest(bandRequest, competencies, trainingCourses);

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void createBand_shouldReturnId() {
        BandWithDetailsRequest bandWithDetailsRequest = new BandWithDetailsRequest(bandRequest, competencies, trainingCourses);
        int response = APP.client().target("http://localhost:8080/api/bands")
                .request()
                .post(Entity.json(bandWithDetailsRequest), Response.class)
                .readEntity(Integer.class);
        Assertions.assertNotNull(response);
    }

    @Test
    void createBand_shouldReturn400_whenInvalid() {
        bandRequest.setResponsibilities("");
        BandWithDetailsRequest bandWithDetailsRequest = new BandWithDetailsRequest(bandRequest, competencies, trainingCourses);

        Response response = APP.client().target("http://localhost:8080/api/bands")
                .request()
                .post(Entity.json(bandWithDetailsRequest), Response.class);
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void getBandById_shouldReturnBand() {
        Band response = APP.client().target("http://localhost:8080/api/bands/1")
                .request()
                .get(Band.class);

        Assertions.assertEquals(1, response.getId());
    }

    @Test
    void getBand_shouldReturn400_whenIDNotFound() {
        Response responseBand = APP.client().target("http://localhost:8080/api/bands/" + 0)
                .request()
                .get(Response.class);

        int response = responseBand.getStatus();
        System.out.println(response);

        Assertions.assertEquals(400, response);
    }

    @Test
    void updateBand_shouldReturn200() {
        Response response = APP.client().target("http://localhost:8080/api/bands/1")
                .request()
                .put(Entity.json(bandWithDetailsRequest), Response.class);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void updateBand_shouldReturn400_whenIDNotFound() {
        Response response = APP.client().target("http://localhost:8080/api/bands/" + 0)
                .request()
                .put(Entity.json(bandWithDetailsRequest), Response.class);

        Assertions.assertEquals(400, response.getStatus());
    }
}
