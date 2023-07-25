package unit.controller;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.JobRoleController;
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
    new Band(1 , "Band 1", "Level 1", "Responsibilities"),
    new Capability(1, "Software Engineering", "Software Engineering"));



    @Test
    void getJobRoles_shouldReturnOK_whenServiceReturnsList() throws FailedToGetJobRoleException {

        List<JobRole> sampleJobRoles = new ArrayList<>();
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);
        sampleJobRoles.add(jobRole);

        Mockito.when(jobRoleService.getAllJobRoles()).thenReturn(sampleJobRoles);

        Response response = jobRoleController.getJobRoles();
        assert(response.getStatus() == 200);
    }

    @Test
    void getJobRoles_shouldReturnInternalServerError_whenServiceThrowsException() throws FailedToGetJobRoleException {
        Mockito.when(jobRoleService.getAllJobRoles()).thenThrow(FailedToGetJobRoleException.class);

        Response response = jobRoleController.getJobRoles();
        assert(response.getStatus() == 500);
    }

}
