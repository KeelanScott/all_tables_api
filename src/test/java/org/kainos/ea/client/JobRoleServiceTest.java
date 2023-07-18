package org.kainos.ea.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobRoleServiceTest {
//
//    EmployeeDao employeeDao = Mockito.mock(EmployeeDao.class);
//    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
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
//    Connection conn;
//
//    @Test
//    void insertEmployee_shouldReturnId_whenDaoReturnsId() throws DatabaseConnectionException, SQLException {
//        int expectedResult = 1;
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.insertEmployee(employeeRequest, conn)).thenReturn(expectedResult);
//
//        int result = employeeService.insertEmployee(employeeRequest);
//
//        assertEquals(result, expectedResult);
//    }
//
//    @Test
//    void insertEmployee_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.insertEmployee(employeeRequest, conn)).thenThrow(SQLException.class);
//
//        assertThrows(SQLException.class,
//                () -> employeeService.insertEmployee(employeeRequest));
//    }
//
//    /*
//    Mocking Exercise 1
//
//    Write a unit test for the getEmployee method
//
//    When the dao throws a SQLException
//
//    Expect SQLException to be thrown
//
//    This should pass without code changes
//     */
//    @Test
//    void getEmployee_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.getEmployee(1, conn)).thenThrow(SQLException.class);
//
//        assertThrows(SQLException.class,
//                () -> employeeService.getEmployee(1));
//    }
//
//    /*
//    Mocking Exercise 2
//
//    Write a unit test for the getEmployee method
//
//    When the dao returns an employee
//
//    Expect the employee to be returned
//
//    This should pass without code changes
//     */
//    @Test
//    void getEmployee_shouldReturnEmployee_whenDaoReturnsEmployee() throws SQLException, DatabaseConnectionException, UserDoesNotExistException {
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.getEmployee(1, conn)).thenReturn(employee);
//
//        assertEquals(employee, employeeService.getEmployee(1));
//    }
//
//    /*
//    Mocking Exercise 4
//
//    Write a unit test for the getEmployee method
//
//    When the dao returns null
//
//    Expect UserDoesNotExistException to be thrown
//
//    This should fail, make code changes to make this test pass
//     */
//    @Test
//    void getEmployee_shouldThrowUserDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException {
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.getEmployee(1, conn)).thenReturn(null);
//
//        assertThrows(UserDoesNotExistException.class, () -> employeeService.getEmployee(1));
//    }
//
//    /*
//    Mocking Exercise 5
//
//    Write a unit test for the getEmployees method
//
//    When the dao returns a list of employees
//
//    Expect the list of employees to be returned
//
//    This should pass without code changes
//     */
//    @Test
//    void getEmployees_shouldReturnEmployeesList_whenDaoReturnsEmployeesList() throws SQLException, DatabaseConnectionException {
//        List<Employee> list = new ArrayList<>();
//        list.add(employee);
//        list.add(employee);
//        list.add(employee);
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.getEmployees(conn)).thenReturn(list);
//
//        assertEquals(list, employeeService.getEmployees());
//    }
//
//    /*
//    Mocking Exercise 6
//
//    Write a unit test for the getEmployees method
//
//    When the dao throws a SQLException
//
//    Expect SQLException to be thrown
//
//    This should pass without code changes
//     */
//    @Test
//    void getEmployees_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
//        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        Mockito.when(employeeDao.getEmployees(conn)).thenThrow(SQLException.class);
//
//        assertThrows(SQLException.class,
//                () -> employeeService.getEmployees());
//    }
}