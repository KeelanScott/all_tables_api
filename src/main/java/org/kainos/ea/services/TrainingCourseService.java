package org.kainos.ea.services;

import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exceptions.FailedToCreateBandTrainingCourseException;
import org.kainos.ea.exceptions.FailedToGetTrainingCoursesException;
import org.kainos.ea.models.BandTrainingCourse;
import org.kainos.ea.models.TrainingCourse;

import java.sql.SQLException;
import java.util.List;

public class TrainingCourseService {

    private final TrainingCourseDao trainingCourseDao;

    public TrainingCourseService(TrainingCourseDao trainingCourseDao) {
        this.trainingCourseDao = trainingCourseDao;
    }
    public List<TrainingCourse> getAllTrainingCourses() throws FailedToGetTrainingCoursesException {
        List<TrainingCourse> trainingCourseListList = null;
        try {
            trainingCourseListList = trainingCourseDao.getAllTrainingCourses();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetTrainingCoursesException();
        }
        return trainingCourseListList;
    }

    public int createBandTrainingCourse(BandTrainingCourse bandTrainingCourse) throws FailedToCreateBandTrainingCourseException {
        try {
            int id = trainingCourseDao.createBandTrainingCourse(bandTrainingCourse);

            if (id == -1) throw new FailedToCreateBandTrainingCourseException();

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateBandTrainingCourseException();
        }
    }
}
