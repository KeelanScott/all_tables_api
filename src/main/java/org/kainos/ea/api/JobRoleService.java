package org.kainos.ea.api;

import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.model.JobRole;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.core.JobRoleValidator;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();

    private JobRoleValidator jobRoleValidator = new JobRoleValidator();

    public JobRoleService(JobRoleDao jobRoleDao){
        this.jobRoleDao = jobRoleDao;
    }

    public JobRoleService(){

    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRoleException, DatabaseConnectionException {
        try {
            List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();

            return jobRoleList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRoleException();
        }
    }

    public JobRole getJobRolesById(int id) throws FailedToGetJobRoleException, DatabaseConnectionException {
        try{
            JobRole jobRole = jobRoleDao.getJobRoleById(id);

            if (jobRole != null) {
                return jobRole;
            }
            else{
                throw new FailedToGetJobRoleException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRoleException();
        }
    }
}