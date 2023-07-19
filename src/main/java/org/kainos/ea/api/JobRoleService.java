package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToGetJobRoleException;
import org.kainos.ea.core.JobRoleValidator;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;

import java.sql.Connection;
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

    public List<JobRole> getAllJobRoles() throws SQLException {
        List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();
        return jobRoleList;
    }

    public JobRole getJobRolesById(int id) throws FailedToGetJobRoleException {
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