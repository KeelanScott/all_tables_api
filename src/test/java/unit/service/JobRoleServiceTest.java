package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao);

    Band band = new Band(
            1,
            "Band 1",
            "Executive"
    );
    Capability capability = new Capability(
            1,
            "Engineering"
    );
    JobRole jobRole = new JobRole(
            1,
            "Tim",
            band,
            capability
    );

    @Test
    void getAllJobRoles_shouldReturnJobRoleList_whenDaoReturnsJobRoleList() throws SQLException, FailedToGetJobRoleException {
        ArrayList<JobRole> list = new ArrayList<>();
        list.add(jobRole);
        list.add(jobRole);
        list.add(jobRole);

        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(list);

        assertEquals(list, jobRoleService.getAllJobRoles());
    }

    @Test
    void getAllJobRoles_shouldThrowFailedToGetJobRoleException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(jobRoleDao.getAllJobRoles()).thenThrow(SQLException.class);
    }
}