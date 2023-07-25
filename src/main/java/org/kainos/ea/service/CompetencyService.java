package org.kainos.ea.service;


import org.kainos.ea.exception.FailedToCreateBandCompetencyException;
import org.kainos.ea.exception.FailedToGetCompetenciesException;
import org.kainos.ea.exception.InvalidBandCompetencyException;
import org.kainos.ea.model.BandCompetency;
import org.kainos.ea.model.Competency;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.validator.BandCompetencyValidator;
import java.sql.SQLException;
import java.util.List;

public class CompetencyService {
    private final CompetencyDao competencyDao;

    public CompetencyService(CompetencyDao competencyDao) {
        this.competencyDao = competencyDao;
    }
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
            bandCompetencyValidator.isValidBandCompetency(bandCompetency);

            int id = competencyDao.createBandCompetency(bandCompetency);

            if (id == -1) throw new FailedToCreateBandCompetencyException();

            return id;
        } catch (SQLException e) {
            System.err.println(e);
            throw new FailedToCreateBandCompetencyException();
        }
    }
}
