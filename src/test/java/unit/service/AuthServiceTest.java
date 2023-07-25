package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToEncryptTokenException;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.Login;
import org.kainos.ea.service.AuthService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    AuthDao authDao = Mockito.mock(AuthDao.class);
    AuthService authService = new AuthService(authDao);

    Login login = new Login(
            "admin",
            "admin"
    );
    @Test
    void login_shouldThrowFailedToGenerateTokenException_whenDaoGenerateTokenThrowsSqlException() throws SQLException, DatabaseConnectionException, FailedToEncryptTokenException {
        Login validLogin = new Login(
                "keelan@gmail.com",
                "Scott"
        );

        Mockito.when(authDao.validLogin(validLogin)).thenReturn(true);
        Mockito.when(authDao.generateToken(validLogin.getEmail())).thenThrow(new SQLException());

        assertThrows(FailedToGenerateTokenException.class, () -> authService.login(validLogin));
    }

    @Test
    void login_shouldThrowFailedToLoginException_whenDaoValidLoginThrowsSqlException() throws DatabaseConnectionException {

        Mockito.when(authDao.validLogin(login)).thenReturn(false);

        assertThrows(FailedToLoginException.class, () -> authService.login(login));
    }


}



