package org.kainos.ea.service;

import org.kainos.ea.model.JobRole;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.dao.JobRoleDao;
import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao;

    public JobRoleService(JobRoleDao jobRoleDao){
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRole> getAllJobRoles() throws FailedToGetJobRoleException {
        try {
            return this.jobRoleDao.getAllJobRoles();
        } catch (SQLException e) {
            System.out.println(e);
            throw new FailedToGetJobRoleException();
        }
    }
}