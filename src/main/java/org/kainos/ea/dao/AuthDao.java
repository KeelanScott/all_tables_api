package org.kainos.ea.dao;
import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.model.Login;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.TokenExpiredException;
import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class AuthDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public boolean validLogin(Login login) throws DatabaseConnectionException, SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT password FROM users WHERE email = '"
                + login.getEmail() + "';");

        while (rs.next()) {
            return rs.getString("password").equals(login.getPassword());
        }
        return false;
    }

    public String generateToken(String email) throws SQLException, DatabaseConnectionException {
        String token = UUID.randomUUID().toString();
        Date expiry = DateUtils.addHours(new Date(), 8);

        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO tokens (email, token, expiry) VALUES (?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement);

        st.setString(1, email);
        st.setString(2, token);
        st.setTimestamp(3, new java.sql.Timestamp(expiry.getTime()));

        st.executeUpdate();

        return token;
    }

    public boolean register(Login login) throws SQLException, DatabaseConnectionException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO users (email, password, is_admin) VALUES (?,?, ?)";

        PreparedStatement stl = c.prepareStatement(insertStatement);
        stl.setString(1, login.getEmail());
        stl.setString(2, login.getPassword());
        stl.setInt(3, 0);
        stl.executeUpdate();

        return true;
    }

    public boolean isEmailTaken(String email) throws SQLException, DatabaseConnectionException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT email FROM `users` " +
                "WHERE email = '" + email + "'");

        return rs.next();
    }

    public void deleteUser(String email) throws SQLException, DatabaseConnectionException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();
        st.executeUpdate("DELETE FROM `users` WHERE email = '" + email + "'");
    }
}
