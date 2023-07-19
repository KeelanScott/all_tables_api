package org.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.dao.DatabaseConnector;
import org.kainos.ea.dao.JobRoleDao;
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
            "Tim",
            2,
            "Develops Software"
    );

    Band band = new Band(
            1,
            "Band 1",
            1
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

    Connection conn;

    @Test
    void getAllJobRoles_shouldReturnJobRoleList_whenDaoReturnsJobRoleList() throws SQLException, FailedToGetJobRoleException {
        ArrayList<JobRole> list = new ArrayList<>();
        list.add(jobRole);
        list.add(jobRole);
        list.add(jobRole);

        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(list);

        assertEquals(list, jobRoleService.getAllJobRoles());
    }
}
