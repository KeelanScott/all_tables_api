package org.kainos.ea.services;


import org.kainos.ea.models.Competency;
import org.kainos.ea.models.CompetencyElement;
import org.kainos.ea.models.Level;
import org.kainos.ea.exceptions.FailedToGetCompetenciesException;
import org.kainos.ea.exceptions.FailedToGetCompetencyElementsException;
import org.kainos.ea.exceptions.FailedToGetLevelsException;
import org.kainos.ea.dao.CompetencyDao;

import java.sql.SQLException;
import java.util.List;

public class CompetencyService {
    private final CompetencyDao competencyDao = new CompetencyDao();

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

    public List<Level> getAllLevels() throws FailedToGetLevelsException {
        List<Level> levelList = null;
        try {
            levelList = competencyDao.getAllLevels();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetLevelsException();
        }

        return levelList;
    }

    public List<CompetencyElement> getCompetencyElements(int competencyId) throws FailedToGetCompetencyElementsException {
        List<CompetencyElement> elementList = null;
        try {
            elementList = competencyDao.getCompetencyElements(competencyId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCompetencyElementsException();
        }

        return elementList;
    }
}
