package org.kainos.ea.service;
import org.kainos.ea.dao.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;
import org.kainos.ea.dao.AuthDao;

import java.sql.*;
import java.util.Date;

public class AuthService {
    public AuthService(AuthDao authDao){
        this.authDao= authDao;
    }
    private AuthDao authDao;
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException {
        try {
            if (authDao.validLogin(login)) {
                return authDao.generateToken(login.getEmail());
            }

            throw new FailedToLoginException();
        }
        catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToGenerateTokenException();
        }
    }

    public boolean isAdmin(String token) throws TokenExpiredException, FailedToVerifyTokenException, SQLException, DatabaseConnectionException {
        ResultSet user = authDao.getUserFromToken(token);

        return user.getBoolean("is_admin");
    }

    public boolean isRegistered(ResultSet user) throws TokenExpiredException, FailedToVerifyTokenException, DatabaseConnectionException, SQLException {
        String email = user.getString("email");
        if (email != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTokenExpired(String token) throws TokenExpiredException, FailedToVerifyTokenException, DatabaseConnectionException, SQLException {
        //boolean isExpired = false;
        ResultSet user = authDao.getUserFromToken(token);

        Timestamp expiry = user.getTimestamp("expiry");

        if (expiry.after(new Date())) {
            return true;
        } else {
            throw new TokenExpiredException();
        }
    }
}
