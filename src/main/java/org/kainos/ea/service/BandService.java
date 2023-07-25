package org.kainos.ea.service;

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
    private final BandValidator bandValidator = new BandValidator();

    public BandService(BandDao bandDao) {
        this.bandDao = bandDao;
    }

    public int createBand(BandRequest bandRequest) throws FailedToCreateBandException, InvalidBandException {
        try {
            bandValidator.isValidBand(bandRequest);

            int id = bandDao.createBand(bandRequest);

            if (id == -1) throw new FailedToCreateBandException();

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateBandException();
        }
    }

    public List<Band> getAllBands() throws FailedToGetBandException {
        try {
            return bandDao.getAllBands();
        } catch (SQLException e) {
            throw new FailedToGetBandException();
        }
    }

    public Band getBandById(int id) throws FailedToGetBandException {
        try {
            return bandDao.getBandById(id);
        } catch (SQLException e) {
            throw new FailedToGetBandException();
        }
    }
}