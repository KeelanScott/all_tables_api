package unit.controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.controller.AuthController;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.Login;
import org.kainos.ea.service.AuthService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.ws.rs.core.Response;


@ExtendWith(MockitoExtension.class)
public class AuthControllerUnitTest {
    AuthService authService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(authService);

    Login login = new Login(
            "admin@email.com",
            "Adminxx!"
    );
    String token = "4677678787878";

    @Test
    void login_shouldReturnOK_whenLoginSuccessful() throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException {
        Mockito.when(authService.login(login)).thenReturn(token);
        Response response = authController.login(login);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void login_shouldReturnBAD_whenFailedToLogin() throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException {
        Mockito.when(authService.login(login)).thenThrow(FailedToLoginException.class);
        Response response = authController.login(login);

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void login_shouldReturnInternalServerError_whenFailedToGenerateTokenExceptionThrown() throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException {
        Mockito.when(authService.login(login)).thenThrow(new FailedToGenerateTokenException());
        Response response = authController.login(login);

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void login_shouldReturnInternalServerError_whenDatabaseConnectionExceptionThrown() throws FailedToLoginException, FailedToGenerateTokenException, DatabaseConnectionException {
        Mockito.when(authService.login(login)).thenThrow(new DatabaseConnectionException());
        Response response = authController.login(login);

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void register_shouldReturnOK_whenRegisterSuccessful() throws FailedToGenerateTokenException, InvalidLoginException, FailedToRegisterException {
        Mockito.when(authService.register(login)).thenReturn(true);
        Response response = authController.register(login);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void register_shouldReturn400_whenInvalidLogin() throws FailedToGenerateTokenException, InvalidLoginException, FailedToRegisterException {
        Mockito.when(authService.register(login)).thenThrow(InvalidLoginException.class);
        Response response = authController.register(login);

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void register_shouldReturn500_whenFailedToGenerateTokenExceptionThrown() throws FailedToGenerateTokenException, InvalidLoginException, FailedToRegisterException {
        Mockito.when(authService.register(login)).thenThrow(new FailedToGenerateTokenException());
        Response response = authController.register(login);

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void register_shouldReturn500_whenFailedToRegisterExceptionThrown() throws FailedToGenerateTokenException, InvalidLoginException, FailedToRegisterException {
        Mockito.when(authService.register(login)).thenThrow(new FailedToRegisterException());
        Response response = authController.register(login);

        Assertions.assertEquals(500, response.getStatus());
    }
}