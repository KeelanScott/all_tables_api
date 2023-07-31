package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.Band;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandDao {
    public List<Band> getAllBands() throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String selectStatement = "SELECT name, level, responsibilities FROM bands;";

        PreparedStatement st = c.prepareStatement(selectStatement);

        ResultSet rs = st.executeQuery();

        List<Band> bands = new ArrayList<>();

        while (rs.next()) {
            Band band = new Band(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("level"),
                    rs.getString("responsibilities")
            );

            bands.add(band);
        }

        return bands;
    }

    public Band getBandById(int id) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String selectStatement = "SELECT name, level, responsibilities FROM bands WHERE id = ?;";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            return new Band(
                    id,
                    rs.getString("name"),
                    rs.getString("level"),
                    rs.getString("responsibilities")
            );
        }

        return null;
    }
}
