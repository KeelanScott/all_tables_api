package org.kainos.ea.db;

import org.kainos.ea.cli.JobRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {


    public List<JobRole> getAllJobRoles() throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        assert c != null;
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name, band_id FROM job_roles;");

        List<JobRole> delivery_employee_list = new ArrayList<>();

        while (rs.next()) {
            JobRole delivery_employee = new JobRole (
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("band_id")
            );

            delivery_employee_list.add(delivery_employee);
        }
        return delivery_employee_list;
    }

    public JobRole getJobRoleById(int id) throws SQLException{
        Connection c = DatabaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name, band_id, specification FROM job_roles WHERE id = " + id + ";");

        while (rs.next()) {
            return new JobRole(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("band_id"),
                    rs.getString("specification")
            );

        }

        return null;
    }
}
