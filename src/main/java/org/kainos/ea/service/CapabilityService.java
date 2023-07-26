package org.kainos.ea.service;

import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Capability;
import java.sql.SQLException;
import java.util.List;

public class CapabilityService {
    private CapabilityDao jobRoleDao;

    public CapabilityService(CapabilityDao jobRoleDao){
        this.jobRoleDao = jobRoleDao;
    }

    public List<Capability> getAllCapabilities() throws FailedToGetCapabilityException, DatabaseConnectionException {
        try {
            return this.jobRoleDao.getAllCapabilities();
        } catch (SQLException e) {
            throw new FailedToGetCapabilityException();
        }
    }

    public Capability getCapabilityById(int id) throws FailedToGetCapabilityException, DatabaseConnectionException, CapabilityDoesNotExistException {
        try{
            Capability capability = jobRoleDao.getCapabilityById(id);

            if (capability != null) {
                return capability;
            }
            else{
                throw new CapabilityDoesNotExistException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCapabilityException();
        }
    }
}