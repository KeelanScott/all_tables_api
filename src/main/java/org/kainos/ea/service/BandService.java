package org.kainos.ea.service;

import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.*;
import org.kainos.ea.validator.BandCompetencyValidator;
import org.kainos.ea.validator.BandValidator;
import org.kainos.ea.dao.BandDao;
import java.sql.SQLException;

public class BandService {
    private final BandDao bandDao;
    private final CompetencyDao competencyDao;
    private final TrainingCourseDao trainingCourseDao;
    private final BandValidator bandValidator;
    private final BandCompetencyValidator bandCompetencyValidator;

    public BandService(BandDao bandDao, CompetencyDao competencyDao, TrainingCourseDao trainingCourseDao, BandValidator bandValidator, BandCompetencyValidator bandCompetencyValidator) {
        this.bandDao = bandDao;
        this.competencyDao = competencyDao;
        this.trainingCourseDao = trainingCourseDao;
        this.bandValidator = bandValidator;
        this.bandCompetencyValidator = bandCompetencyValidator;
    }

    public int createBand(BandWithDetailsRequest bandWithDetailsRequest) throws FailedToCreateBandException, InvalidBandException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        try {
            bandValidator.isValidBand(bandWithDetailsRequest.getBand());

            for (BandCompetencyRequest bandCompetency : bandWithDetailsRequest.getBandCompetencies()) {
                bandCompetencyValidator.isValidBandCompetency(bandCompetency);
            }

            int id = bandDao.createBand(bandWithDetailsRequest.getBand());
            if (id == -1) throw new FailedToCreateBandException();

            for (BandCompetencyRequest bandCompetency : bandWithDetailsRequest.getBandCompetencies()) {
                int result = competencyDao.createBandCompetency(bandCompetency, id);
                if (result == -1) throw new FailedToCreateBandCompetencyException();
            }

            for (int trainingCourseId : bandWithDetailsRequest.getTrainingCourses()) {
                int result = trainingCourseDao.createBandTrainingCourse(id, trainingCourseId);
                if (result == -1) throw new FailedToCreateBandTrainingCourseException();
            }

            return id;
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateBandException();
        }
    }
}
