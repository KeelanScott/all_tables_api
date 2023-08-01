package org.kainos.ea.service;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.validator.JobRoleValidator;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {
    private final JobRoleDao jobRoleDao;

    private final JobRoleValidator jobRoleValidator;

    public JobRoleService(JobRoleDao jobRoleDao, JobRoleValidator jobRoleValidator){
        this.jobRoleDao = jobRoleDao;
        this.jobRoleValidator = jobRoleValidator;
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
            jobRoleValidator.isValidJobRole(jobRoleRequest);
            return jobRoleDao.createJobRole(jobRoleRequest);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateJobRoleException();
        }
    }

    public boolean updateJobRole(int id, JobRoleRequest jobRoleRequest) throws FailedToUpdateJobRoleException, InvalidJobRoleException, JobRoleDoesNotExistException {
        try {
            jobRoleValidator.isValidJobRole(jobRoleRequest, id);
            return jobRoleDao.updateJobRole(id, jobRoleRequest);
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateJobRoleException();
        }
    }

    public boolean deleteJobRole(int id) throws FailedToDeleteJobRoleException, JobRoleDoesNotExistException {
        try {
            if(jobRoleDao.getJobRoleById(id) == null){
                throw new JobRoleDoesNotExistException();
            }
            return jobRoleDao.deleteJobRole(id);
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteJobRoleException();
        }
    }
}