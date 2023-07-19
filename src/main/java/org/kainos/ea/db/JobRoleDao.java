package org.kainos.ea.db;

import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.DatabaseConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {


    public List<JobRole> getAllJobRoles() throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        assert c != null;
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT job_roles.id, job_roles.name, bands.`name`, level, bands.id  FROM job_roles JOIN bands ON job_roles.band_id = bands.id;");

        List<JobRole> jobRoleList = new ArrayList<>();

        while (rs.next()) {
            Band band = new Band(
                    rs.getInt("bands.id"),
                    rs.getString("bands.name"),
                    rs.getInt("level")
            );
            JobRole jobRole = new JobRole (
                    rs.getInt("job_roles.id"),
                    rs.getString("job_roles.name"),
                    band
            );

            jobRoleList.add(jobRole);
        }
        return jobRoleList;
    }

    public JobRole getJobRoleById(int id) throws SQLException, DatabaseConnectionException {
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
