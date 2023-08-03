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
}