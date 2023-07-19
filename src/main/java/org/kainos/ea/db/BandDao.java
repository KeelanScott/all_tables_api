package org.kainos.ea.db;

import org.kainos.ea.cli.BandRequest;

import java.sql.*;

public class BandDao {

    public int createBand(BandRequest bandRequest) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO bands(name, level, responsibilities)" +
                " VALUES(?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, bandRequest.getName());
        st.setInt(2, bandRequest.getLevelId());
        st.setString(3, bandRequest.getResponsibilities());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }
}
