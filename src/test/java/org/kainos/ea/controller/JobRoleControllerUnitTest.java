package org.kainos.ea.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

@ExtendWith(MockitoExtension.class)
public class JobRoleControllerUnitTest {
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);
    JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    Band band = new Band(
            1,
            "Band 1",
            "1"
    );
    JobRole jobRole = new JobRole(
            1,
            "Software Engineer",
            band,
            "Develops software"
    );

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
