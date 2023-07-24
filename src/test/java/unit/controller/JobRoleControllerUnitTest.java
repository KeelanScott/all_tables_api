package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleControllerUnitTest {
    JobRoleService jobRoleService =   Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    JobRole jobRole = new JobRole(1,
            "Software Engineer",
            new Band(1 , "Band 1", "Level 1"),
            new Capability(1, "Software Engineering", "Software Engineering"),
            "Spec"
    );

    @Test
    void getJobRoles_shouldReturnOK_whenServiceReturnsList() throws FailedToGetJobRoleException, DatabaseConnectionException {

        List<JobRole> sampleJobRoles = new ArrayList<>();
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);

        Mockito.when(jobRoleService.getAllJobRoles()).thenReturn(sampleJobRoles);

        Response response = jobRoleController.getJobRoles();
        assert(response.getStatus() == 200);
    }

    @Test
    void getJobRoles_shouldReturnBadRequest_whenServiceThrowsFailedToGetJobRoleException() throws FailedToGetJobRoleException, DatabaseConnectionException {
        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(FailedToGetJobRoleException.class);

        Response response = jobRoleController.getJobRoles();
        assert(response.getStatus() == 400);
    }

    @Test
    void getJobRoleById_shouldReturnBadRequest_whenServiceThrowFailedToGetJobRoleException() throws DatabaseConnectionException, FailedToGetJobRoleException {
        Mockito.when(jobRoleService.getJobRolesById(0)).thenThrow(FailedToGetJobRoleException.class);
        assert(400 == jobRoleController.getJobRoleById(0).getStatus());
    }

    @Test
    void getJobRoleById_shouldReturnServerError_whenServiceThrowDatabaseConnectionException() throws DatabaseConnectionException, FailedToGetJobRoleException {
        Mockito.when(jobRoleService.getJobRolesById(1)).thenThrow(DatabaseConnectionException.class);
        Response response = jobRoleController.getJobRoleById(1);
        System.out.println(response.getStatus());
        assert(500 == response.getStatus());
    }

    @Test
    void getJobRoleById_shouldReturnJobRole_whenServiceReturnsJobRole() throws DatabaseConnectionException, FailedToGetJobRoleException {
        Mockito.when(jobRoleService.getJobRolesById(1)).thenReturn(jobRole);
        Response response= jobRoleController.getJobRoleById(1);
        assert(200 == response.getStatus());
    }

}
