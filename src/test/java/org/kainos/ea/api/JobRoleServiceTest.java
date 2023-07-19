package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.dao.DatabaseConnector;
import org.kainos.ea.service.JobRoleService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao);

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Software Engineer",
            2,
            "Develops Software"
    );

    Band band = new Band(
            1,
            "Band 1",
            1
    );
    JobRole jobRole = new JobRole(
            1,
            "Software Engineer",
            band,
            "Develops software"
    );

    Connection conn;

    @Test
    void getJobRolesById_shouldThrowFailedToGetJobRoleException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException {
        //Mockito.when(DatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getJobRoleById(0)).thenReturn(null);

        assertThrows(FailedToGetJobRoleException.class, () -> jobRoleService.getJobRolesById(0));
    }

    @Test
    void getJobRolesById_shouldReturnJobRole_whenDaoReturnsJobRole() throws SQLException, FailedToGetJobRoleException, DatabaseConnectionException {
        //Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getJobRoleById(1)).thenReturn(jobRole);

        assertEquals(jobRole, jobRoleService.getJobRolesById(1));
    }

    @Test
    void getAllJobRoles_shouldReturnJobRoleList_whenDaoReturnsJobRoleList() throws SQLException, DatabaseConnectionException, FailedToGetJobRoleException {
        ArrayList<JobRole> list = new ArrayList<>();
        list.add(jobRole);
        list.add(jobRole);
        list.add(jobRole);

        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(list);

        assertEquals(list, jobRoleService.getAllJobRoles());
    }
}
