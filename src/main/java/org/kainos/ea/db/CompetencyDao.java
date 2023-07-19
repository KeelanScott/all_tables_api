package org.kainos.ea.db;

import org.kainos.ea.cli.Competency;
import org.kainos.ea.cli.CompetencyElement;
import org.kainos.ea.cli.Level;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetencyDao {
    public List<Competency> getAllCompetencies() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name " +
                "FROM competencies;");

        List<Competency> competencyList = new ArrayList<>();

        while (rs.next()) {
            Competency competency = new Competency(
                    rs.getInt("competencies.id"),
                    rs.getString("competencies.name"));
            competencyList.add(competency);
        }

        return competencyList;
    }

    public List<Level> getAllLevels() throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name " +
                "FROM levels;");

        List<Level> levelList = new ArrayList<>();

        while (rs.next()) {
            Level level = new Level(
                    rs.getInt("levels.id"),
                    rs.getString("levels.name"));
            levelList.add(level);
        }

        return levelList;
    }

    public List<CompetencyElement> getCompetencyElements(int competencyId) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, competency_id, name " +
                "FROM competency_elements WHERE competency_id = " + competencyId + ";");

        List<CompetencyElement> elementsList = new ArrayList<>();

        while (rs.next()) {
            CompetencyElement level = new CompetencyElement(
                    rs.getInt("competency_elements.id"),
                    rs.getInt("competency_elements.competency_id"),
                    rs.getString("competency_elements.name"));
            elementsList.add(level);
        }

        return elementsList;
    }
}
