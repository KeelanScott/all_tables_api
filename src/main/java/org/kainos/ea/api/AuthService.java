package org.kainos.ea.api;

import org.kainos.ea.cli.Login;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToVerifyTokenException;
import org.kainos.ea.client.TokenExpiredException;
import org.kainos.ea.db.AuthDao;

import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao = new AuthDao();

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException {
        if (authDao.validLogin(login)) {
            try {
                return authDao.generateToken(login.getUsername());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
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
            boolean is_admin = authDao.getIsAdminFromToken(token);

            if (is_admin == true || is_admin == false) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }
}
