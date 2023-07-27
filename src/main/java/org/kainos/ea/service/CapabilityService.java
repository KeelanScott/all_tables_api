package org.kainos.ea.service;

import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Capability;
import java.sql.SQLException;
import java.util.List;

public class CapabilityService {
    private final CapabilityDao jobRoleDao;

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
}