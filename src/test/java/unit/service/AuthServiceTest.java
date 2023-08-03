package unit.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;
import org.kainos.ea.service.AuthService;
import org.kainos.ea.validator.AuthValidator;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    AuthDao authDao = Mockito.mock(AuthDao.class);
    AuthValidator authValidator = Mockito.mock(AuthValidator.class);
    AuthService authService = new AuthService(authDao, authValidator);

    Login login = new Login(
            "admin@email.com",
            "Adminxx!"
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

    @Test
    void register_shouldGenerateToken_whenSuccessfulRegister() throws SQLException, DatabaseConnectionException, FailedToRegisterException, FailedToGenerateTokenException, InvalidLoginException {
        Mockito.when(authDao.register(login)).thenReturn(true);
        Mockito.when(authDao.generateToken(login.getEmail())).thenReturn(token);
        Mockito.when(authValidator.isValidLogin(login)).thenReturn(true);

        assertEquals(token, authService.register(login));
    }

    @Test
    void register_shouldThrowFailedToRegisterException_whenDaoGenerateTokenThrowsSqlException() throws SQLException, DatabaseConnectionException, InvalidLoginException {
        Mockito.when(authDao.register(login)).thenReturn(true);
        Mockito.when(authDao.generateToken(login.getEmail())).thenThrow(new SQLException());
        Mockito.when(authValidator.isValidLogin(login)).thenReturn(true);

        assertThrows(FailedToRegisterException.class, () -> authService.register(login));
    }

    @Test
    void register_shouldThrowFailedToRegisterException_whenDaoRegisterThrowsSqlException() throws SQLException, DatabaseConnectionException, InvalidLoginException {
        Mockito.when(authDao.register(login)).thenThrow(new SQLException());
        Mockito.when(authValidator.isValidLogin(login)).thenReturn(true);

        assertThrows(FailedToRegisterException.class, () -> authService.register(login));
    }

    @Test
    void register_shouldThrowInvalidLoginException_whenAuthValidatorIsValidLoginThrowsInvalidLoginException() throws InvalidLoginException, DatabaseConnectionException, SQLException {
        Mockito.when(authValidator.isValidLogin(login)).thenThrow(new InvalidLoginException("Invalid login"));

        assertThrows(InvalidLoginException.class, () -> authService.register(login));
    }
}

