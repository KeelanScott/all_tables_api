package org.kainos.ea.service;

import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Capability;
import java.sql.SQLException;
import java.util.List;

public class CapabilityService {
    private final CapabilityDao capabilityDao;

    public CapabilityService(CapabilityDao capabilityDao){
        this.capabilityDao = capabilityDao;
    }

    public List<Capability> getAllCapabilities() throws FailedToGetCapabilityException, DatabaseConnectionException {
        try {
            return this.capabilityDao.getAllCapabilities();
        } catch (SQLException e) {
            throw new FailedToGetCapabilityException();
        }
    }
}