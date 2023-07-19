package org.kainos.ea.api;


import org.kainos.ea.cli.Competency;
import org.kainos.ea.client.FailedToGetCompetenciesException;
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
}
