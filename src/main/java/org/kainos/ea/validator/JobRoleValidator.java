package org.kainos.ea.validator;

import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.service.BandService;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.service.JobRoleService;

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
        try {
            if ( (new BandService(new BandDao())).getBandById(jobRoleRequest.getBandId()) != null) {
                throw new InvalidJobRoleException("Band ID does not exist");
            } if ( (new CapabilityService(new CapabilityDao())).getCapabilityById(jobRoleRequest.getCapabilityId()) != null) {
                throw new InvalidJobRoleException("Capability ID does not exist");
            }
            return true;
        } catch (DatabaseConnectionException |
                 FailedToGetBandException | BandDoesNotExistException | FailedToGetCapabilityException |
                 CapabilityDoesNotExistException e) {
            throw new InvalidJobRoleException("Failed to validate job role");
        }
    }
}
