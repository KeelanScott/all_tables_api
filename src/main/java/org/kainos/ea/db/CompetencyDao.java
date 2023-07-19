package org.kainos.ea.db;

import org.kainos.ea.cli.Competency;

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
            Competency client = new Competency(
                    rs.getInt("competencies.id"),
                    rs.getString("competencies.name"));
            competencyList.add(client);
        }

        return competencyList;
    }
}
