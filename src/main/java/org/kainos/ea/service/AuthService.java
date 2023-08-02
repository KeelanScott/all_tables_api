package org.kainos.ea.service;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;
import org.kainos.ea.dao.AuthDao;
import java.sql.SQLException;

public class AuthService {
    public AuthService(AuthDao authDao){
        this.authDao= authDao;
    }
    private AuthDao authDao;

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
}
