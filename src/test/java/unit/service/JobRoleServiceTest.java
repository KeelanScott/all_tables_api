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
import org.kainos.ea.validator.JobRoleValidator;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);
    JobRoleService jobRoleService = new JobRoleService(jobRoleDao, jobRoleValidator);

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
        Mockito.when(jobRoleDao.createJobRole(jobRoleRequest)).thenReturn(1);
        Mockito.when(jobRoleValidator.isValidJobRole(jobRoleRequest)).thenReturn(true);
        assertEquals(1, jobRoleService.createJobRole(jobRoleRequest));
    }

    @Test
    void updateJobRole_shouldReturnTrue_whenDaoReturnsTrue() throws SQLException, DatabaseConnectionException, InvalidJobRoleException, FailedToUpdateJobRoleException, JobRoleDoesNotExistException {
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(jobRole);
        Mockito.when(jobRoleDao.updateJobRole(1, jobRoleRequest)).thenReturn(true);
        assertTrue(jobRoleService.updateJobRole(1, jobRoleRequest));
    }

    @Test
    void updateJobRole_shouldThrowJobRoleDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException {
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(null);
        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.updateJobRole(1, jobRoleRequest));
    }

    @Test
    void updateJobRole_shouldThrowFailedToUpdateJobRoleException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(jobRole);
        Mockito.when(jobRoleDao.updateJobRole(1, jobRoleRequest)).thenThrow(SQLException.class);
        assertThrows(FailedToUpdateJobRoleException.class, () -> jobRoleService.updateJobRole(1, jobRoleRequest));
    }

    @Test
    void updateJobRole_shouldThrowInvalidJobRoleException_whenInvalidJobRole() throws InvalidJobRoleException, DatabaseConnectionException, SQLException {
        Mockito.when(jobRoleValidator.isValidJobRole(jobRoleRequest)).thenThrow(InvalidJobRoleException.class);
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(jobRole);
        assertThrows(InvalidJobRoleException.class, () -> jobRoleService.updateJobRole(1, jobRoleRequest));
    }

    @Test
    void deleteJobRole_shouldReturnTrue_whenDaoReturnsTrue() throws SQLException, DatabaseConnectionException, FailedToDeleteJobRoleException, JobRoleDoesNotExistException {
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(jobRole);
        Mockito.when(jobRoleDao.deleteJobRole(1)).thenReturn(true);
        assertTrue(jobRoleService.deleteJobRole(1));
    }

    @Test
    void deleteJobRole_shouldThrowJobRoleDoesNotExistException_whenDaoReturnsFalse() throws SQLException, DatabaseConnectionException {
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(null);
        assertThrows(JobRoleDoesNotExistException.class, () -> jobRoleService.deleteJobRole(1));
    }
}