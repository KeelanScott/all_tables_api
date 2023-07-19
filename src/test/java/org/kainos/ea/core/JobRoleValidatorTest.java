package org.kainos.ea.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.JobRoleRequest;
import org.kainos.ea.client.JobRoleNameTooLongException;
import org.kainos.ea.client.JobRoleSpecificationTooLongException;

public class JobRoleValidatorTest {

    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
    public void isValidJobRole_shouldReturnNull_whenValidEmployee() throws JobRoleSpecificationTooLongException, JobRoleNameTooLongException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "James",
                1,
                "Spec"
        );

        Assertions.assertNull(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

//    @Test
//    public void isValidEmployee_shouldThrowSalaryTooLowException_whenSalaryTooLow() {
//        EmployeeRequest employeeRequest = new EmployeeRequest(
//                10000,
//                "Tim",
//                "Bloggs",
//                "tbloggs@email.com",
//                "1 Main Street",
//                "Main Road",
//                "Belfast",
//                "Antrim",
//                "BT99BT",
//                "Northern Ireland",
//                "12345678901",
//                "12345678",
//                "AA1A11AA"
//        );
//
//        assertThrows(SalaryTooLowException.class,
//                () -> employeeValidator.isValidEmployee(employeeRequest));
//    }

}
