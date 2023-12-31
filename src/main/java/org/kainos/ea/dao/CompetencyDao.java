package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetencyDao {
    public List<Competency> getAllCompetencies() throws SQLException, DatabaseConnectionException {
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

    public Competency getCompetencyById(int id) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name " +
                "FROM competencies " +
                "WHERE id = " + id + ";");

        if (rs.next()) {
            Competency competency = new Competency(
                    rs.getInt("competencies.id"),
                    rs.getString("competencies.name"));
            return competency;
        }

        return null;
    }

    public int createBandCompetency(BandCompetencyRequest bandCompetency, int bandId) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO bands_competencies(band_id, competency_id, description)" +
                " VALUES(?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, bandId);
        st.setInt(2, bandCompetency.getCompetencyID());
        st.setString(3, bandCompetency.getDescription());

        // Compound primary key, so can't use rs.next to get generated id
        int result = st.executeUpdate();

        if (result == 0) return -1;
        else return result;
    }

    public int deleteBandCompetencies(int bandId) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String deleteStatement = "DELETE FROM bands_competencies WHERE band_id = ?;";

        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1, bandId);

        return st.executeUpdate();
    }

    public List<BandCompetency> getBandCompetencies(int id) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT bands_competencies.band_id, bands_competencies.band_id, bands_competencies.competency_id, bands_competencies.description " +
                "FROM bands_competencies " +
                "WHERE bands_competencies.band_id = " + id + ";");

        List<BandCompetency> bandCompetencyList = new ArrayList<>();

        while (rs.next()) {
            BandCompetency bandCompetency = new BandCompetency(
                    rs.getInt("bands_competencies.band_id"),
                    rs.getInt("bands_competencies.competency_id"),
                    rs.getString("bands_competencies.description"));
            bandCompetencyList.add(bandCompetency);
        }

        return bandCompetencyList;
    }
}
