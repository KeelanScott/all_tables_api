package unit.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.model.JobRoleRequest;
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
    BandDao bandDao = Mockito.mock(BandDao.class);
    CapabilityDao capabilityDao = Mockito.mock(CapabilityDao.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao, bandDao, capabilityDao);

    Band band = new Band(
            1,
            "Band 1",
            "Executive",
            "spec"
    );
    Capability capability = new Capability(
            1,
            "Engineering"
    );
    JobRole jobRole = new JobRole(
            1,
            "Tim",
            band,
            capability,
            "spec"
    );

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Job Role",
            1,
            1,
            "spec"
    );

    @Test
    void getAllJobRoles_shouldReturnJobRoleList_whenDaoReturnsJobRoleList() throws SQLException, FailedToGetJobRoleException, DatabaseConnectionException {
        ArrayList<JobRole> list = new ArrayList<>();
        list.add(jobRole);
        list.add(jobRole);
        list.add(jobRole);

        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(list);

        assertEquals(list, jobRoleService.getAllJobRoles());
    }

    @Test
    void getAllJobRoles_shouldThrowFailedToGetJobRoleException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(jobRoleDao.getAllJobRoles()).thenThrow(SQLException.class);

        assertThrows(FailedToGetJobRoleException.class, () -> jobRoleService.getAllJobRoles());
    }

    @Test
    void getJobRolesById_shouldThrowJobRoleDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException {
        Mockito.when(jobRoleDao.getJobRoleById(0)).thenReturn(null);
        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.getJobRolesById(0));
    }

    @Test
    void getJobRolesById_shouldReturnJobRole_whenDaoReturnsJobRole() throws SQLException, FailedToGetJobRoleException, DatabaseConnectionException, JobRoleDoesNotExistException {
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(jobRole);
        assertEquals(jobRole, jobRoleService.getJobRolesById(1));
    }

    @Test
    void getJobRolesById_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenThrow(SQLException.class);
        assertThrows(FailedToGetJobRoleException.class, () -> jobRoleService.getJobRolesById(1));
    }

    @Test
    void createJobRole_shouldReturnId_whenDaoReturnsId() throws SQLException, DatabaseConnectionException, InvalidJobRoleException, FailedToCreateJobRoleException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(capabilityDao.getCapabilityById(1)).thenReturn(capability);
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenReturn(1);
        assertEquals(1, jobRoleService.createJobRole(jobRoleRequest));
    }
}
