package org.kainos.ea.core;


import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.exception.JobRoleNameTooLongException;
import org.kainos.ea.exception.JobRoleSpecificationTooLongException;

public class JobRoleValidator {

    public String isValidJobRole(JobRoleRequest jobRoleRequest) throws JobRoleNameTooLongException, JobRoleSpecificationTooLongException {
        if (jobRoleRequest.getName().length() > 50) {
            throw new JobRoleNameTooLongException();
        }
        if (jobRoleRequest.getSpecification().length() > 255) {
            throw new JobRoleSpecificationTooLongException();
        }

        return null;
    }
}
