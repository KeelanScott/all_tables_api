package integration.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;

import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerIntegrationTest {

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Job Role",
            1,
            1,
            "spec"
    );
    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnList() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

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

    @Test
    void createJobRole_shouldReturn201_whenCreated() {
        Response response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .post(javax.ws.rs.client.Entity.json(jobRoleRequest));

        Assertions.assertEquals(200, response.getStatus());
    }
}