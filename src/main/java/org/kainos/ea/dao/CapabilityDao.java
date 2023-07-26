package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.Capability;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {
    public List<Capability> getAllCapabilities() throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name, description FROM capabilities");

        List<Capability> capabilityList = new ArrayList<>();

        while (rs.next()) {
            Capability capability = new Capability(
                    rs.getInt("capabilities.id"),
                    rs.getString("capabilities.name"),
                    rs.getString("capabilities.description")
            );

            capabilityList.add(capability);
        }
        return capabilityList;
    }

}
