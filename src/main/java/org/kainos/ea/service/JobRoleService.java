package org.kainos.ea.service;

import org.kainos.ea.model.JobRole;
import org.kainos.ea.exception.FailedToGetJobRoleException;
import org.kainos.ea.dao.JobRoleDao;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();

    public JobRoleService(JobRoleDao jobRoleDao){
        this.jobRoleDao = jobRoleDao;
    }


    public List<JobRole> getAllJobRoles() throws FailedToGetJobRoleException {
        try {
            return jobRoleDao.getAllJobRoles();
        } catch (SQLException e) {
            throw new FailedToGetJobRoleException();
        }
    }

//    public JobRole getJobRolesById(int id) throws FailedToGetJobRoleException {
//        try{
//            JobRole jobRole = jobRoleDao.getJobRoleById(id);
//
//            if (jobRole != null) {
//                return jobRole;
//            }
//            else{
//                throw new FailedToGetJobRoleException();
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            throw new FailedToGetJobRoleException();
//        }
//    }
}