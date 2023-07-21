package org.kainos.ea.services;


import org.kainos.ea.exceptions.*;
import org.kainos.ea.models.*;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.validators.BandCompetencyValidator;

import java.sql.SQLException;
import java.util.List;

public class CompetencyService {
    private final CompetencyDao competencyDao = new CompetencyDao();
    private final BandCompetencyValidator bandCompetencyValidator = new BandCompetencyValidator();

    public List<Competency> getAllCompetencies() throws FailedToGetCompetenciesException {
        List<Competency> competencyList = null;
        try {
            competencyList = competencyDao.getAllCompetencies();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCompetenciesException();
        }

        return competencyList;
    }

    public int createBandCompetency(BandCompetency bandCompetency) throws FailedToCreateBandCompetencyException, InvalidBandCompetencyException {
        try {
            String validation = bandCompetencyValidator.isValidBandCompetency(bandCompetency);
            if (validation != null) throw new InvalidBandCompetencyException(validation);

            int id = competencyDao.createBandCompetency(bandCompetency);

            if (id == -1) throw new FailedToCreateBandCompetencyException();

            return id;
        } catch (SQLException e) {
            System.err.println(e);
            throw new FailedToCreateBandCompetencyException();
        }
    }
}
