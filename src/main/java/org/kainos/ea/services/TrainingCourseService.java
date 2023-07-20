package org.kainos.ea.services;

import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exceptions.FailedToGetTrainingCourseException;
import org.kainos.ea.models.TrainingCourse;

import java.sql.SQLException;
import java.util.List;

public class TrainingCourseService {

    private final TrainingCourseDao trainingCourseDao = new TrainingCourseDao();

    public List<TrainingCourse> getAllTrainingCourses() throws FailedToGetTrainingCourseException {
        List<TrainingCourse> trainingCourseListList = null;
        try {
            trainingCourseListList = trainingCourseDao.getAllTrainingCourses();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetTrainingCourseException();
        }
        return trainingCourseListList;
    }
}
