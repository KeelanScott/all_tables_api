package org.kainos.ea.validator;

import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRoleRequest;

public class JobRoleValidator {
    public static boolean isValidJobRole(JobRoleRequest jobRoleRequest) throws InvalidJobRoleException {
        if (jobRoleRequest.getName() == null || jobRoleRequest.getName().isEmpty()) {
            throw new InvalidJobRoleException("Job role name cannot be empty");
        }

        return true;
    }
}
