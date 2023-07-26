package org.kainos.ea.service;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.validator.JobRoleValidator;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private JobRoleDao jobRoleDao;

    public JobRoleService(JobRoleDao jobRoleDao){
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRoleException, DatabaseConnectionException {
        try {
            return this.jobRoleDao.getAllJobRoles();
        } catch (SQLException e) {
            throw new FailedToGetJobRoleException();
        }
    }

    public JobRole getJobRolesById(int id) throws FailedToGetJobRoleException, DatabaseConnectionException, JobRoleDoesNotExistException {
        try{
            JobRole jobRole = jobRoleDao.getJobRoleById(id);

            if (jobRole != null) {
                return jobRole;
            }
            else{
                throw new JobRoleDoesNotExistException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRoleException();
        }
    }

    public int createJobRole(JobRoleRequest jobRoleRequest) throws FailedToCreateJobRoleException, DatabaseConnectionException, InvalidJobRoleException {
        try {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
            return jobRoleDao.createJobRole(jobRoleRequest);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateJobRoleException();
        }
    }
}