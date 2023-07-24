package org.kainos.ea.services;

import org.kainos.ea.models.Band;
import org.kainos.ea.models.BandRequest;
import org.kainos.ea.exceptions.FailedToCreateBandException;
import org.kainos.ea.exceptions.InvalidBandException;
import org.kainos.ea.validators.BandValidator;
import org.kainos.ea.dao.BandDao;

import java.sql.SQLException;
import java.util.List;

public class BandService {
    private final BandDao bandDao = new BandDao();
    private final BandValidator bandValidator = new BandValidator();

    public int createBand(BandRequest bandRequest) throws FailedToCreateBandException, InvalidBandException {
        try {
            String validation = bandValidator.isValidBand(bandRequest);
            if (validation != null) throw new InvalidBandException(validation);

            int id = bandDao.createBand(bandRequest);

            if (id == -1) throw new FailedToCreateBandException();

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateBandException();
        }
    }

    public List<Band> getAllBands() throws SQLException {
        return bandDao.getAllBands();
    }

    public Band getBandById(int id) throws SQLException {
        return bandDao.getBandById(id);
    }
}
