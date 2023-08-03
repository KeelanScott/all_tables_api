package org.kainos.ea.service;

import org.kainos.ea.exception.*;
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
        Competency competency = null;
        try {
            competency = competencyDao.getCompetencyById(id);
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCompetencyException();
        }

        if (competency == null) throw new CompetencyDoesNotExistException();

        return competency;
    }
}
