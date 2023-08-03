package org.kainos.ea.service;
import org.kainos.ea.dao.DatabaseConnector;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.validator.AuthValidator;

import java.sql.SQLException;

import java.sql.*;
import java.util.Date;

public class AuthService {
    private AuthDao authDao;
    private final AuthValidator authValidator;

    public AuthService(AuthDao authDao, AuthValidator authValidator){
        this.authDao = authDao;
        this.authValidator = authValidator;
    }

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

    public boolean register(Login login) throws FailedToGenerateTokenException, FailedToRegisterException, InvalidLoginException {
        try{
            authValidator.isValidLogin(login);
            return authDao.register(login);
        } catch (SQLException | DatabaseConnectionException e ) {
            throw new FailedToRegisterException();
        }
    }
}