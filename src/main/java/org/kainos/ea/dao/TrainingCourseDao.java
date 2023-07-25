package org.kainos.ea.dao;

import org.kainos.ea.model.BandTrainingCourse;
import org.kainos.ea.model.TrainingCourse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingCourseDao {
    public List<TrainingCourse> getAllTrainingCourses() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name, description FROM training_courses;");

        List<TrainingCourse> trainingCourseList = new ArrayList<>();

        while (rs.next()) {
            TrainingCourse trainingCourse = new TrainingCourse(
                    rs.getInt("training_courses.id"),
                    rs.getString("training_courses.name"),
                    rs.getString("training_courses.description"));
            trainingCourseList.add(trainingCourse);
        }
        return trainingCourseList;
    }

    public int createBandTrainingCourse(BandTrainingCourse bandTrainingCourse) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        System.out.println("\n\nBand ID: " + bandTrainingCourse.getBandId());
        System.out.println("\nTraining course ID: " + bandTrainingCourse.getBandId());

        String insertStatement = "INSERT INTO band_training_courses(band_id, training_course_id)" +
                " VALUES(?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, bandTrainingCourse.getBandId());
        st.setInt(2, bandTrainingCourse.getTrainingId());

        // Compound primary key, so can't use rs.next to get generated id
        int result = st.executeUpdate();

        if (result == 0) return -1;
        else return result;
    }
}