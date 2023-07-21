package org.kainos.ea.dao;

import org.kainos.ea.models.*;

import java.sql.*;
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

    public int createBandCompetency(BandCompetency bandCompetency) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO bands_competencies(band_id, competency_id, description)" +
                " VALUES(?,?,?);";

        System.out.println(bandCompetency.getDescription());

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, bandCompetency.getBandID());
        st.setInt(2, bandCompetency.getCompetencyID());
        st.setString(3, bandCompetency.getDescription());

        // Compound primary key, so can't use rs.next to get generated id
        int result = st.executeUpdate();

        if (result == 0) return -1;
        else return result;
    }
}
