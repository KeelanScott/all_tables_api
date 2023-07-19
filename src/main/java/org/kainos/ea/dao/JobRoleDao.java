package org.kainos.ea.dao;

import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {


    public List<JobRole> getAllJobRoles() throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        assert c != null;
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                "SELECT job_roles.id, job_roles.name, bands.`name`, level, bands.id, capabilities.id, capabilities.name  FROM job_roles JOIN bands ON job_roles.band_id = bands.id " +
                        "JOIN capabilities ON job_roles.capability_id = capabilities.id;");

        List<JobRole> jobRoleList = new ArrayList<>();

        while (rs.next()) {
            Capability capability = new Capability(
                    rs.getInt("capabilities.id"),
                    rs.getString("capabilities.name")
            );
            Band band = new Band(
                    rs.getInt("bands.id"),
                    rs.getString("bands.name"),
                    rs.getInt("level")
            );
            JobRole jobRole = new JobRole (
                    rs.getInt("job_roles.id"),
                    rs.getString("job_roles.name"),
                    band,
                    capability
            );

            jobRoleList.add(jobRole);
        }
        return jobRoleList;
    }

    public JobRole getJobRoleById(int id) throws SQLException, DatabaseConnectionException {
        Connection c = DatabaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT job_roles.id, job_roles.name, bands.`name`, level, job_roles.specification  FROM job_roles JOIN bands ON job_roles.band_id = bands.id " +
                "JOIN capabilities ON job_roles.capability_id = capabilities.id WHERE job_roles.id = " + id + ";");

        while (rs.next()) {

            Band band = new Band();
            band.setName(rs.getString("bands.name"));
            band.setLevel(rs.getInt("level"));

            return new JobRole(
                    rs.getInt("job_roles.id"),
                    rs.getString("job_roles.name"),
                    band,
                    rs.getString("job_roles.specification")
            );

        }

        return null;
    }
}
