package org.kainos.ea.resources;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.JobRole;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerIntegrationTest {

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoleById_shouldReturnJobRole() {
        JobRole response = APP.client().target("http://localhost:8080/api/job-roles/1")
                .request()
                .get(JobRole.class);

        Assertions.assertEquals(1, response.getId());
    }

    @Test
    void getJobRoleById_shouldReturn400_whenIDNotFound() {
        Response responseJobRole = APP.client().target("http://localhost:8080/api/job-roles/" + 0)
                .request()
                .get(Response.class);

        Assertions.assertEquals(400, responseJobRole.getStatus());
    }


}
