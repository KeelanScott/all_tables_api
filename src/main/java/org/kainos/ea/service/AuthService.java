package org.kainos.ea.service;

import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;

import org.kainos.ea.dao.AuthDao;

import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao = new AuthDao();

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException {
        if (authDao.validLogin(login)) {
            try {
                return authDao.generateToken(login.getEmail());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
            } catch (DatabaseConnectionException e) {
                throw new RuntimeException(e);
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
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean isRegistered(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            boolean is_admin = authDao.getIsAdminFromToken(token);

            if (is_admin == true || is_admin == false) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
