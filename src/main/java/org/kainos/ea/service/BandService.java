package org.kainos.ea.service;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.dao.BandDao;

import java.sql.SQLException;
import java.util.List;

public class BandService {

    private BandDao bandDao;

    public BandService(BandDao bandDao) {
        this.bandDao = bandDao;
    }

    public List<Band> getAllBands() throws FailedToGetBandException, DatabaseConnectionException {
        try {
            return bandDao.getAllBands();
        } catch (SQLException e) {
            throw new FailedToGetBandException();
        }
    }
}
