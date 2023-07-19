package org.kainos.ea.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.exception.JobRoleNameTooLongException;
import org.kainos.ea.exception.JobRoleSpecificationTooLongException;

public class JobRoleValidatorTest {

    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
    public void isValidJobRole_shouldReturnNull_whenValidEmployee() throws JobRoleSpecificationTooLongException, JobRoleNameTooLongException {
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                1,
                "Spec"
        );

        Assertions.assertNull(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldThrowJobRoleNameTooLongException_whenNameTooLong(){
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Svbiuvuiviuerbviuerbviubviuabuvibeuvvbsiuvbsaiubviasuuvbisuabviubviuaviubviubdiuvbiuvdbiubfviusdbviubdviubdiuvbsdufbvusdffbvudbvk",
                1,
                "Spec"
        );

        Assertions.assertThrows(JobRoleNameTooLongException.class,
                () -> jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldThrowJobRoleSpecificationTooLongException_whenSpecificationTooLong(){
        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                1,
                "SvbiuvuiviuerbviuerbviubviuabuvibeuvvbsiuvbsaiubviasuuvbisuabviubviuaviubviubdiuvbiuvdbiubfviusdbviubdviubdiuvbsdufbvusdffbvudbvkSvbiuvuiviuerbviuerbviubviuabuvibeuvvbsiuvbsaiubviasuuvbisuabviubviuaviubviubdiuvbiuvdbiubfviusdbviubdviubdiuvbsdufbvusdffbvudbvk"
        );

        Assertions.assertThrows(JobRoleSpecificationTooLongException.class,
                () -> jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

}
