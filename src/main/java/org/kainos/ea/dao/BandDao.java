package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;
import java.sql.*;

public class BandDao {
    public int createBand(BandRequest bandRequest) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO bands(name, level, responsibilities)" +
                " VALUES(?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, bandRequest.getName());
        st.setString(2, bandRequest.getLevel());
        st.setString(3, bandRequest.getResponsibilities());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public int updateBand(int id, BandRequest bandRequest) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String updateStatement = "UPDATE bands SET name = ?, level = ?, responsibilities = ? WHERE id = ?;";

        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setString(1, bandRequest.getName());
        st.setString(2, bandRequest.getLevel());
        st.setString(3, bandRequest.getResponsibilities());
        st.setInt(4, id);

        return st.executeUpdate();
    }

    public Band getBandById(int id) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        String selectStatement = "SELECT * FROM bands WHERE id = ?;";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            return new Band(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("level"),
                    rs.getString("responsibilities")
            );
        }

        return null;
    }
}
