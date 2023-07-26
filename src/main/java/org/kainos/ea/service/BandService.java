package org.kainos.ea.service;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.validator.BandValidator;
import org.kainos.ea.dao.BandDao;

import java.sql.SQLException;
import java.util.List;

public class BandService {

    private BandDao bandDao;
    private final BandValidator bandValidator;

    public BandService(BandDao bandDao, BandValidator bandValidator) {
        this.bandDao = bandDao;
        this.bandValidator = bandValidator;
    }

    public int createBand(BandRequest bandRequest) throws FailedToCreateBandException, InvalidBandException {
        try {
            bandValidator.isValidBand(bandRequest);

            int id = bandDao.createBand(bandRequest);

            if (id == -1) throw new FailedToCreateBandException();

            return id;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateBandException();
        }
    }

    public List<Band> getAllBands() throws FailedToGetBandException {
        try {
            return bandDao.getAllBands();
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToGetBandException();
        }
    }

    public Band getBandById(int id) throws FailedToGetBandException {
        try {
            return bandDao.getBandById(id);
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToGetBandException();
        }
    }
}
