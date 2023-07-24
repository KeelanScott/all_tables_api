package integration.controller;

        import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
        import io.dropwizard.testing.junit5.DropwizardAppExtension;
        import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.kainos.ea.all_tables_apiApplication;
        import org.kainos.ea.all_tables_apiConfiguration;
        import org.kainos.ea.models.Band;

        import javax.ws.rs.core.Response;
        import java.util.ArrayList;
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
}
