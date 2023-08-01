package org.kainos.ea.service;

import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToCreateBandTrainingCourseException;
import org.kainos.ea.exception.FailedToGetTrainingCoursesException;
import org.kainos.ea.model.BandTrainingCourse;
import org.kainos.ea.model.TrainingCourse;
import java.sql.SQLException;
import java.util.List;

public class TrainingCourseService {

    private final TrainingCourseDao trainingCourseDao;

    public TrainingCourseService(TrainingCourseDao trainingCourseDao) {
        this.trainingCourseDao = trainingCourseDao;
    }
    public List<TrainingCourse> getAllTrainingCourses() throws FailedToGetTrainingCoursesException {
        try {
            return trainingCourseDao.getAllTrainingCourses();
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetTrainingCoursesException();
        }
    }

    public List<TrainingCourse> getTrainingForBand(int id) throws FailedToGetTrainingCoursesException {
        try {
            return trainingCourseDao.getTrainingForBand(id);
        } catch (SQLException | DatabaseConnectionException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetTrainingCoursesException();
        }
    }
}
