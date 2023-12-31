package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.TrainingCourse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingCourseDao {
    public List<TrainingCourse> getAllTrainingCourses() throws SQLException, DatabaseConnectionException {
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

    public int createBandTrainingCourse(int bandId, int trainingCourseId) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO band_training_courses(band_id, training_course_id)" +
                " VALUES(?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, bandId);
        st.setInt(2, trainingCourseId);

        // Compound primary key, so can't use rs.next to get generated id
        int result = st.executeUpdate();

        if (result == 0) return -1;
        else return result;
    }

    public int deleteBandTrainingCourses(int bandId) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String deleteStatement = "DELETE FROM band_training_courses WHERE band_id = ?;";

        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1, bandId);

        return st.executeUpdate();
    }

    public List<Integer> getTrainingForBand(int id) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT training_courses.id, training_courses.name, training_courses.description FROM training_courses " +
                "INNER JOIN band_training_courses ON training_courses.id = band_training_courses.training_course_id " +
                "WHERE band_training_courses.band_id = " + id + ";");

        List<TrainingCourse> trainingCourseList = new ArrayList<>();

        while (rs.next()) {
            TrainingCourse trainingCourse = new TrainingCourse(
                    rs.getInt("training_courses.id"),
                    rs.getString("training_courses.name"),
                    rs.getString("training_courses.description"));
            trainingCourseList.add(trainingCourse);
        }

        return trainingCourseList.stream().map(TrainingCourse::getId).collect(Collectors.toList());
    }
}