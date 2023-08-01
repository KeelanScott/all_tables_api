package integration.controller;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToCreateJobRoleException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.service.JobRoleService;
import org.kainos.ea.validator.JobRoleValidator;

import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerIntegrationTest {

    JobRoleRequest jobRoleRequest;
    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    private int getNewId(){
        return APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .post(javax.ws.rs.client.Entity.json(jobRoleRequest)).readEntity(Integer.class);
    }
    private void deleteJobRole(int id){
        APP.client().target("http://localhost:8080/api/job-roles/"+id)
                .request()
                .delete();
    }

    @BeforeEach
    void resetRequest(){
        jobRoleRequest = new JobRoleRequest(
                "Job Role",
                2,
                1,
                "spec"
        );
    }

    @Test
    void getJobRoles_shouldReturnList() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getJobRoleById_shouldReturnJobRole() {
        int id = this.getNewId();
        JobRole response = APP.client().target("http://localhost:8080/api/job-roles/"+id)
                .request()
                .get(JobRole.class);
        this.deleteJobRole(id);
        Assertions.assertEquals(id, response.getId());
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

        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    void createJobRole_shouldReturn400_whenInvalidData() {
        jobRoleRequest.setName(null);
        Response response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .post(javax.ws.rs.client.Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void updateJobRole_shouldReturn200_whenUpdated() throws DatabaseConnectionException, InvalidJobRoleException, FailedToCreateJobRoleException {
        int id = this.getNewId();
        Response response = APP.client().target("http://localhost:8080/api/job-roles/"+id)
                .request()
                .put(javax.ws.rs.client.Entity.json(jobRoleRequest));
        this.deleteJobRole(id);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void updateJobRole_shouldReturn400_whenIDNotFound() {
        Response response = APP.client().target("http://localhost:8080/api/job-roles/0")
                .request()
                .put(javax.ws.rs.client.Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void updateJobRole_shouldReturn400_whenInvalidRequest() throws DatabaseConnectionException, InvalidJobRoleException, FailedToCreateJobRoleException {
        int id = this.getNewId();
        Response response = APP.client().target("http://localhost:8080/api/job-roles/"+id)
                .request()
                .put(javax.ws.rs.client.Entity.json(new JobRoleRequest(
                        "",
                        1,
                        1,
                        "spec"
                )));
        this.deleteJobRole(id);

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deleteJobRole_shouldReturn200_whenDeleted() throws DatabaseConnectionException, InvalidJobRoleException, FailedToCreateJobRoleException {
        int id = this.getNewId();
        Response response = APP.client().target("http://localhost:8080/api/job-roles/"+id)
                .request()
                .delete();
        Assertions.assertEquals(200, response.getStatus());
    }
}