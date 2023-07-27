package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.JobRoleController;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
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
            new Band(1 , "Band 1", "Level 1", "Spec"),
            new Capability(1, "Software Engineering", "Software Engineering"),
            "Spec"
    );

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Job Role",
            1,
            1,
            "spec"
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
    void getJobRoles_shouldReturnServerError_whenServiceThrowsFailedToGetJobRoleException() throws FailedToGetJobRoleException, DatabaseConnectionException {
        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(FailedToGetJobRoleException.class);

        Response response = jobRoleController.getJobRoles();
        assert(response.getStatus() == 500);
    }

    @Test
    void getJobRoleById_shouldReturnBadRequest_whenServiceThrowJobRoleDoesNotExistException() throws DatabaseConnectionException, FailedToGetJobRoleException, JobRoleDoesNotExistException {
        Mockito.when(jobRoleService.getJobRolesById(0)).thenThrow(JobRoleDoesNotExistException.class);
        assert(400 == jobRoleController.getJobRoleById(0).getStatus());
    }

    @Test
    void getJobRoleById_shouldReturnServerError_whenServiceThrowDatabaseConnectionException() throws DatabaseConnectionException, FailedToGetJobRoleException, JobRoleDoesNotExistException {
        Mockito.when(jobRoleService.getJobRolesById(1)).thenThrow(DatabaseConnectionException.class);
        Response response = jobRoleController.getJobRoleById(1);
        System.out.println(response.getStatus());
        assert(500 == response.getStatus());
    }

    @Test
    void getJobRoleById_shouldReturnServerError_whenServiceThrowFailedToGetJobRoleException() throws DatabaseConnectionException, FailedToGetJobRoleException, JobRoleDoesNotExistException {
        Mockito.when(jobRoleService.getJobRolesById(1)).thenThrow(FailedToGetJobRoleException.class);
        Response response = jobRoleController.getJobRoleById(1);
        System.out.println(response.getStatus());
        assert(500 == response.getStatus());
    }

    @Test
    void getJobRoleById_shouldReturnJobRole_whenServiceReturnsJobRole() throws DatabaseConnectionException, FailedToGetJobRoleException, JobRoleDoesNotExistException {
        Mockito.when(jobRoleService.getJobRolesById(1)).thenReturn(jobRole);
        Response response= jobRoleController.getJobRoleById(1);
        assert(200 == response.getStatus());
    }

    @Test
    void createJobRole_shouldReturn201_whenServiceReturnsId() throws DatabaseConnectionException, FailedToGetJobRoleException, JobRoleDoesNotExistException, InvalidJobRoleException, FailedToCreateJobRoleException {
        Mockito.when(jobRoleService.createJobRole(jobRoleRequest)).thenReturn(1);
        Response response= jobRoleController.createJobRole(jobRoleRequest);
        assert(201 == response.getStatus());
    }

    @Test
    void createJobRole_shouldReturnServerError_whenServiceThrowsFailedToCreateJobRoleException() throws DatabaseConnectionException, FailedToGetJobRoleException, JobRoleDoesNotExistException, InvalidJobRoleException, FailedToCreateJobRoleException {
        Mockito.when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(FailedToCreateJobRoleException.class);
        Response response= jobRoleController.createJobRole(jobRoleRequest);
        assert(500 == response.getStatus());
    }

    @Test
    void createJobRole_shouldReturnServerError_whenServiceThrowsInvalidJobRoleException() throws DatabaseConnectionException, FailedToGetJobRoleException, JobRoleDoesNotExistException, InvalidJobRoleException, FailedToCreateJobRoleException {
        Mockito.when(jobRoleService.createJobRole(jobRoleRequest)).thenThrow(InvalidJobRoleException.class);
        Response response= jobRoleController.createJobRole(jobRoleRequest);
        assert(400 == response.getStatus());
    }
}