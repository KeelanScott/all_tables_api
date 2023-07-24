package org.kainos.ea.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGenerateTokenException;
import org.kainos.ea.exception.FailedToLoginException;
import org.kainos.ea.model.Login;
import org.kainos.ea.service.AuthService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerUnitTest {
    AuthService authService = Mockito.mock(AuthService.class);
    AuthDao authDao = Mockito.mock(AuthDao.class);
    AuthController authController = new AuthController(authService);

    Login login = new Login(
            "admin",
            "admin"
    );
    @Test
    void login_shouldReturnOK_whenLoginSuccessful() throws DatabaseConnectionException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        String token = "4677678787878";
        Login validLogin = new Login(
                "keelan@gmail.com",
                "Scott"
        );
        Mockito.when(authService.login(validLogin)).thenReturn(token);

        Response response = authController.login(validLogin);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void login_shouldReturnBAD_whenFailedToLogin() throws DatabaseConnectionException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        Mockito.when(authService.login(login)).thenThrow(FailedToLoginException.class);

        Response response = authController.login(login);
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void login_shouldReturnInternalServerError_whenFailedToGenerateToken() throws DatabaseConnectionException, FailedToLoginException, FailedToGenerateTokenException, SQLException {
        Login validLogin = new Login(
                "keelan@gmail.com",
                "Scott"
        );

        Mockito.when(authService.login(validLogin)).thenThrow(new FailedToGenerateTokenException());

        Response response = authController.login(validLogin);
        Assertions.assertEquals(500, response.getStatus());
    }
}




