package org.kainos.ea.service;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.JobRoleDoesNotExistException;
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
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            throw new FailedToGetJobRoleException();
        }
    }

    public JobRole getJobRolesById(int id) throws FailedToGetJobRoleException, JobRoleDoesNotExistException, DatabaseConnectionException {
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
}