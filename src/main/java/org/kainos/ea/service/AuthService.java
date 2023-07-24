package org.kainos.ea.service;

import org.kainos.ea.dao.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;

import org.kainos.ea.dao.AuthDao;

import java.sql.SQLException;

public class AuthService {

    public AuthService(AuthDao authDao){
        this.authDao= authDao;
    }
    private AuthDao authDao;


    //private DatabaseConnector databaseConnector = new DatabaseConnector();

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException {
        if (authDao.validLogin(login)) {
            try {
                return authDao.generateToken(login.getEmail());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
            } catch (DatabaseConnectionException e) {
                throw new DatabaseConnectionException();
            }
        }
        throw new FailedToLoginException();
    }

    public boolean isAdmin(String token) throws TokenExpiredException, FailedToVerifyTokenException, DatabaseConnectionException {
        try {
            boolean is_admin = authDao.getIsAdminFromToken(token);

            if (is_admin == true) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException();
        }

        return false;
    }

    public boolean isRegistered(String token) throws TokenExpiredException, FailedToVerifyTokenException, DatabaseConnectionException {
        try {
          boolean isRegistered = authDao.getIsUserFromToken(token);
          return isRegistered;
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        } catch (DatabaseConnectionException e) {
            throw new DatabaseConnectionException();
        }
    }
}
