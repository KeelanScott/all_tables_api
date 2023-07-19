package org.kainos.ea.api;


import org.kainos.ea.cli.Competency;
import org.kainos.ea.cli.Level;
import org.kainos.ea.client.FailedToGetCompetenciesException;
import org.kainos.ea.client.FailedToGetLevelsException;
import org.kainos.ea.db.CompetencyDao;

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
}
