package org.kainos.ea.service;

import org.kainos.ea.exception.*;
import org.kainos.ea.model.Competency;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.validator.BandCompetencyValidator;
import java.sql.SQLException;
import java.util.List;

public class CompetencyService {
    private final CompetencyDao competencyDao;
    private final BandCompetencyValidator bandCompetencyValidator;

    public CompetencyService(CompetencyDao competencyDao, BandCompetencyValidator bandCompetencyValidator) {
        this.competencyDao = competencyDao;
        this.bandCompetencyValidator = bandCompetencyValidator;
    }

    public List<Competency> getAllCompetencies() throws FailedToGetCompetenciesException {
        List<Competency> competencyList = null;
        try {
            competencyList = competencyDao.getAllCompetencies();
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCompetenciesException();
        }

        return competencyList;
    }

    public Competency getCompetencyById(int id) throws FailedToGetCompetencyException, CompetencyDoesNotExistException {
        try {
            Competency competency = competencyDao.getCompetencyById(id);

            if (competency != null) {
                return competency;
            } else {
                throw new CompetencyDoesNotExistException();
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCompetencyException();
        }
    }
}
