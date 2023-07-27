package integration.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.Competency;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CompetencyControllerIntegrationTest {

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getAllCompetencies_shouldReturnList() {
        List<Competency> response = APP.client().target("http://localhost:8080/api/competencies")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getCompetencyById_shouldReturnCompetency() {
        Competency competency = APP.client().target("http://localhost:8080/api/competencies/1")
                .request()
                .get(Competency.class);

        Assertions.assertEquals(1, competency.getCompetencyId());
    }

    @Test
    void getCompetencyById_shouldReturn400_whenIDNotFound() {
        Response competency = APP.client().target("http://localhost:8080/api/competencies/0")
                .request()
                .get(Response.class);

        Assertions.assertEquals(400, competency.getStatus());
    }
}
