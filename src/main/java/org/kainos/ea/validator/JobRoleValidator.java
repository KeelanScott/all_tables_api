package org.kainos.ea.validator;

import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRoleRequest;

public class JobRoleValidator {
    public static boolean isValidJobRole(JobRoleRequest jobRoleRequest) throws InvalidJobRoleException {
        if (jobRoleRequest.getName() == null || jobRoleRequest.getName().isEmpty()) {
            throw new InvalidJobRoleException("Job role name cannot be empty");
        }
        if (jobRoleRequest.getBandId() == 0) {
            throw new InvalidJobRoleException("Band ID cannot be 0");
        }
        if (jobRoleRequest.getCapabilityId() == 0) {
            throw new InvalidJobRoleException("Capability ID cannot be 0");
        }
        if (jobRoleRequest.getSpecification() == null || jobRoleRequest.getSpecification().isEmpty()) {
            throw new InvalidJobRoleException("Specification cannot be empty");
        }

        return true;
    }
}
