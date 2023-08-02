package unit.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.exception.*;
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
    String token = "73768rr37734r87678368";

    @Test
    void login_shouldGenerateToken_whenSuccessfulLogin() throws SQLException, DatabaseConnectionException, FailedToLoginException, FailedToGenerateTokenException {
        Mockito.when(authDao.validLogin(login)).thenReturn(true);
        Mockito.when(authDao.generateToken(login.getEmail())).thenReturn(token);

        assertEquals(token, authService.login(login));
    }

    @Test
    void login_shouldThrowFailedToGenerateTokenException_whenDaoGenerateTokenThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(authDao.validLogin(login)).thenReturn(true);
        Mockito.when(authDao.generateToken(login.getEmail())).thenThrow(new SQLException());

        assertThrows(FailedToGenerateTokenException.class, () -> authService.login(login));
    }

    @Test
    void login_shouldThrowFailedToLoginException_whenDaoValidLoginThrowsSqlException() throws DatabaseConnectionException, SQLException {
        Mockito.when(authDao.validLogin(login)).thenReturn(false);

        assertThrows(FailedToLoginException.class, () -> authService.login(login));
    }
}

