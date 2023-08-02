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
    void login_shouldThrowFailedToGenerateTokenException_whenDaoGenerateTokenThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(authDao.validLogin(login)).thenReturn(true);
        Mockito.when(authDao.generateToken(login.getEmail())).thenThrow(new SQLException());

        assertThrows(FailedToGenerateTokenException.class, () -> authService.login(login));
    }

    @Test
    void login_shouldThrowFailedToLoginException_whenDaoValidLoginThrowsSqlException() throws DatabaseConnectionException {
        Mockito.when(authDao.validLogin(login)).thenReturn(false);

        assertThrows(FailedToLoginException.class, () -> authService.login(login));
    }

    @Test
    void isAdmin_shouldThrowFailedToVerifyTokenException_whenDaoGetIsAdminFromTokenThrowsSqlException() throws DatabaseConnectionException, TokenExpiredException, SQLException {
        Mockito.when(authDao.getIsAdminFromToken(token)).thenThrow(SQLException.class);

        assertThrows(FailedToVerifyTokenException.class, () -> authService.isAdmin(token));
    }

    @Test
    void isAdmin_shouldThrowDatabaseConnectionException_whenDaoGetIsAdminFromTokenThrowsDatabaseConnectionException() throws DatabaseConnectionException, TokenExpiredException, SQLException {
        Mockito.when(authDao.getIsAdminFromToken(token)).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class, () -> authService.isAdmin(token));
    }

    @Test
    void isRegistered_shouldThrowFailedToVerifyTokenException_whenDaoGetIsUserFromTokenThrowsFailedToVerifyTokenException() throws DatabaseConnectionException, TokenExpiredException, SQLException {
        Mockito.when(authDao.getIsUserFromToken(token)).thenThrow(SQLException.class);

        assertThrows(FailedToVerifyTokenException.class, () -> authService.isRegistered(token));
    }

    @Test
    void isRegistered_shouldThrowDatabaseConnectionException_whenDaoGetIsUserFromTokenThrowsDatabaseConnectionException() throws DatabaseConnectionException, TokenExpiredException, SQLException {
        Mockito.when(authDao.getIsUserFromToken(token)).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class, () -> authService.isRegistered(token));
    }
}

