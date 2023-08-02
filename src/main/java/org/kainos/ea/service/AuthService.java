package org.kainos.ea.service;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.validator.AuthValidator;

import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao;
    private final AuthValidator authValidator;

    public AuthService(AuthDao authDao, AuthValidator authValidator){
        this.authDao = authDao;
        this.authValidator = authValidator;
    }

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException {
        if (authDao.validLogin(login)) {
            try {
                return authDao.generateToken(login.getEmail());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
            }
        }
        throw new FailedToLoginException();
    }

    public boolean isAdmin(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            return authDao.getIsAdminFromToken(token);
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToVerifyTokenException();
        }
    }

    public boolean isRegistered(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
          return authDao.getIsUserFromToken(token);
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToVerifyTokenException();
        }
    }

    public String register(Login login) throws FailedToGenerateTokenException, FailedToRegisterException, InvalidLoginException {
        try{
            authValidator.isValidLogin(login);
            if(authDao.register(login)){
                return authDao.generateToken(login.getEmail());
            }
        } catch (SQLException | DatabaseConnectionException e) {
            throw new FailedToRegisterException();
        };
        throw new FailedToRegisterException();
    }
}
