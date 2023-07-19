package org.kainos.ea.api;

import org.kainos.ea.cli.BandRequest;
import org.kainos.ea.client.FailedToCreateBandException;
import org.kainos.ea.client.InvalidBandException;
import org.kainos.ea.core.BandValidator;
import org.kainos.ea.db.BandDao;

import java.sql.SQLException;

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
}
