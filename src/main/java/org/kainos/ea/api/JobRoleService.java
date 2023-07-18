package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.FailedToGetJobRoleException;
import org.kainos.ea.core.JobRoleValidator;
import org.kainos.ea.db.JobRoleDao;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();

    private JobRoleValidator jobRoleValidator = new JobRoleValidator();


    public List<JobRole> getAllJobRoles() throws FailedToGetJobRoleException {
        try {
            List<JobRole> jobRoleList = jobRoleDao.getAllJobRoles();

            return jobRoleList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRoleException();
        }
    }

    public JobRole getJobRolesById(int id) throws FailedToGetJobRoleException {
        try{
            JobRole jobRole = jobRoleDao.getJobRoleById(id);

            return jobRole;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetJobRoleException();
        }
    }
}