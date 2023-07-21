package org.kainos.ea.dao;

import org.kainos.ea.models.TrainingCourse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainingCourseDao {
    public List<TrainingCourse> getAllTrainingCourses() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name FROM training_courses;");

        List<TrainingCourse> trainingCourseList = new ArrayList<>();

        while (rs.next()) {
            TrainingCourse trainingCourse = new TrainingCourse(
                    rs.getInt("training_courses.id"),
                    rs.getString("training_courses.name"));
            trainingCourseList.add(trainingCourse);
        }
        return trainingCourseList;
    }
}
