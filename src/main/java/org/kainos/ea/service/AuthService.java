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

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException, FailedToEncryptTokenException {
        if (authDao.validLogin(login)) {
            try {
                return authDao.generateToken(login.getEmail());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
            } catch (FailedToEncryptTokenException e) {
                throw new FailedToEncryptTokenException();
            }
        }
        throw new FailedToLoginException();
    }

    public boolean isAdmin(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            boolean is_admin = authDao.getIsAdminFromToken(token);

            if (is_admin == true) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }

    public boolean isRegistered(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
          boolean isRegistered = authDao.getIsUserFromToken(token);
          return isRegistered;
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }
    }
}
