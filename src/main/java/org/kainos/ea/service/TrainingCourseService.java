package org.kainos.ea.service;

import org.kainos.ea.dao.TrainingCourseDao;
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
