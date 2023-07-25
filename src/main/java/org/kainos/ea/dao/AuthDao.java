package org.kainos.ea.dao;

import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.encryption.TokenEncryption;
import org.kainos.ea.exception.FailedToEncryptTokenException;
import org.kainos.ea.model.Login;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.TokenExpiredException;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class AuthDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public boolean validLogin(Login login) {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT password FROM users WHERE email = '"
                    + login.getEmail() + "';");

            while (rs.next()) {
                return rs.getString("password").equals(login.getPassword());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public String generateToken(String email) throws SQLException, FailedToEncryptTokenException {
        String token = UUID.randomUUID().toString();
        String encryptedToken;
        Date expiry = DateUtils.addHours(new Date(), 8);
        String key = System.getenv("APP_ENCRYPTION_KEY");


        try {
            encryptedToken = TokenEncryption.encryptToken(token, key);
        } catch (FailedToEncryptTokenException e) {
            throw new FailedToEncryptTokenException();
        }

        Connection c = databaseConnector.getConnection();

        try {
            String insertStatement = "INSERT INTO tokens (email, token, expiry) VALUES (?,?,?)";

            PreparedStatement st = c.prepareStatement(insertStatement);

            st.setString(1, email);
            st.setString(2, token);
            st.setTimestamp(3, new java.sql.Timestamp(expiry.getTime()));

            st.executeUpdate();

            return encryptedToken;
        } catch (Exception e){
            throw new SQLException(e);
        }
    }

    public boolean getIsAdminFromToken(String token) throws SQLException, TokenExpiredException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT is_admin, expiry FROM users join tokens using (email)" + "where token = '" + token + "';");

        while (rs.next()) {
            Timestamp expiry = rs.getTimestamp("expiry");

            if(expiry.after(new Date())) {
                return rs.getBoolean("is_admin");
            } else {
                throw new TokenExpiredException();
            }
        }
        return false;
    }

    public boolean getIsUserFromToken(String token) throws SQLException, TokenExpiredException {
        Connection c = databaseConnector.getConnection();

        boolean isRegistered = false;
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT email, expiry FROM users join tokens using (email)" + "where token = '" + token + "';");

        while (rs.next()) {
            Timestamp expiry = rs.getTimestamp("expiry");
            String email = rs.getString("email");

            if (email != null) {
                isRegistered = true;
            }

            if(expiry.after(new Date())) {
                return isRegistered;
            } else {
                throw new TokenExpiredException();
            }
        }
        return false;
    }
