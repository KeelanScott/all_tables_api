package org.kainos.ea.validator;

import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRoleRequest;
import java.sql.SQLException;

public class JobRoleValidator {

    JobRoleDao jobRoleDao;
    BandDao bandDao;
    CapabilityDao capabilityDao;
    public JobRoleValidator(JobRoleDao jobRoleDao, BandDao bandDao, CapabilityDao capabilityDao) {
        this.jobRoleDao = jobRoleDao;
        this.bandDao = bandDao;
        this.capabilityDao = capabilityDao;
    }
    public boolean isValidJobRole(JobRoleRequest jobRoleRequest) throws InvalidJobRoleException {
        if (jobRoleRequest.getName() == null || jobRoleRequest.getName().isEmpty()) {
            throw new InvalidJobRoleException("Job role name cannot be empty");
        }
        if (jobRoleRequest.getName().length() > 50) {
            throw new InvalidJobRoleException("Job role name cannot be longer than 50 characters");
        }
        if (jobRoleRequest.getBandId() <= 0) {
            throw new InvalidJobRoleException("Band ID cannot be 0 or less");
        }
        if (jobRoleRequest.getCapabilityId() <= 0) {
            throw new InvalidJobRoleException("Capability ID cannot be 0 or less");
        }
        if (jobRoleRequest.getSpecification() == null || jobRoleRequest.getSpecification().isEmpty()) {
            throw new InvalidJobRoleException("Specification cannot be empty");
        }
        if(jobRoleRequest.getSpecification().length() > 255){
            throw new InvalidJobRoleException("Specification cannot be longer than 255 characters");
        }
        try {
            if ( bandDao.getBandById(jobRoleRequest.getBandId()) == null) {
                throw new InvalidJobRoleException("Band ID does not exist");
            }
            if ( capabilityDao.getCapabilityById(jobRoleRequest.getCapabilityId()) == null) {
                throw new InvalidJobRoleException("Capability ID does not exist");
            }
            return true;
        } catch (SQLException | DatabaseConnectionException e) {
            throw new RuntimeException("failed to validate job role");
        }
    }

    public boolean isValidJobRole(JobRoleRequest jobRoleRequest, int id) throws InvalidJobRoleException {
        this.isValidJobRole(jobRoleRequest);
        try {
            if (jobRoleDao.getJobRoleById(id) == null) {
                throw new InvalidJobRoleException("Job role ID does not exist");
            }
            return true;
        } catch (SQLException | DatabaseConnectionException e) {
            throw new RuntimeException("failed to validate job role");
        }
    }
}