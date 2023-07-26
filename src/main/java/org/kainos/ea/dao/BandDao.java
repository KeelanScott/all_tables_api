package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
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
}
