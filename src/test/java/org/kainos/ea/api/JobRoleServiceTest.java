package org.kainos.ea.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToGetJobRoleException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobRoleServiceTest {

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);
    JobRoleService jobRoleService = Mockito.mock(JobRoleService.class);

    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
//
//    EmployeeService employeeService = new EmployeeService(employeeDao, databaseConnector);
//
//    EmployeeRequest employeeRequest = new EmployeeRequest(
//            30000,
//            "Tim",
//            "Bloggs",
//            "tbloggs@email.com",
//            "1 Main Street",
//            "Main Road",
//            "Belfast",
//            "Antrim",
//            "BT99BT",
//            "Northern Ireland",
//            "12345678901",
//            "12345678",
//            "AA1A11AA"
//    );
//
//    Employee employee = new Employee(
//            30000,
//            "Tim",
//            "Bloggs",
//            "tbloggs@email.com",
//            "1 Main Street",
//            "Main Road",
//            "Belfast",
//            "Antrim",
//            "BT99BT",
//            "Northern Ireland",
//            "12345678901",
//            "12345678",
//            "AA1A11AA"
//    );
//
    JobRole jobRole = new JobRole(
            1,
            "Software Engineer",
            2
    );
    Connection conn;

    @Test
    void getAllJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(jobRoleDao.getAllJobRoles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobRoleService.getAllJobRoles());
    }

    @Test
    void getAllJobRoles_shouldReturnEmployeesList_whenDaoReturnsEmployeesList() throws SQLException {
        List<JobRole> list = new ArrayList<>();
        list.add(jobRole);
        list.add(jobRole);
        list.add(jobRole);
//        Mockito.when(DatabaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobRoleDao.getAllJobRoles()).thenReturn(list);

        assertEquals(list, jobRoleService.getAllJobRoles());
    }
}