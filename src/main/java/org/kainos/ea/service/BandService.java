package org.kainos.ea.service;

import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.*;
import org.kainos.ea.validator.BandCompetencyValidator;
import org.kainos.ea.validator.BandValidator;
import org.kainos.ea.dao.BandDao;
import java.sql.SQLException;
import java.util.List;

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

            bandCompetencyValidator.areValidBandCompetencies(bandWithDetailsRequest.getBandCompetencies(), bandCompetencyValidator);

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

    public void updateBand(int id, BandWithDetailsRequest bandWithDetailsRequest) throws FailedToUpdateBandException, InvalidBandException, InvalidBandCompetencyException, FailedToUpdateBandCompetencyException, FailedToUpdateBandTrainingCourseException, BandDoesNotExistException {
        try {
            Band band = bandDao.getBandById(id);
            if (band == null) throw new BandDoesNotExistException();

            bandValidator.isValidBand(bandWithDetailsRequest.getBand());

            for (BandCompetencyRequest bandCompetency : bandWithDetailsRequest.getBandCompetencies()) {
                bandCompetencyValidator.isValidBandCompetency(bandCompetency);
            }

            int result = bandDao.updateBand(id, bandWithDetailsRequest.getBand());
            if (result == -1) throw new FailedToUpdateBandException();

            // Deleting the band competencies and creating them again is safer than updating them as new competencies may have been added
            result = competencyDao.deleteBandCompetencies(id);
            if (result == -1) throw new FailedToUpdateBandCompetencyException();

            for (BandCompetencyRequest bandCompetency : bandWithDetailsRequest.getBandCompetencies()) {
                result = competencyDao.createBandCompetency(bandCompetency, id);
                if (result == -1) throw new FailedToUpdateBandCompetencyException();
            }

            result = trainingCourseDao.deleteBandTrainingCourses(id);
            if (result == -1) throw new FailedToUpdateBandTrainingCourseException();

            for (int trainingCourseId : bandWithDetailsRequest.getTrainingCourses()) {
                result = trainingCourseDao.createBandTrainingCourse(id, trainingCourseId);
                if (result == -1) throw new FailedToUpdateBandTrainingCourseException();
            }
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateBandException();
        }
    }

    public Band getBandById(int id) throws FailedToGetBandException, BandDoesNotExistException {
        try {
            Band band =  bandDao.getBandById(id);
            if (band != null) return band;
            else throw new BandDoesNotExistException();
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToGetBandException();
        }
    }

    public List<Band> getAllBands() throws FailedToGetBandException {
        try {
            return bandDao.getAllBands();
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToGetBandException();
        }
    }
}
