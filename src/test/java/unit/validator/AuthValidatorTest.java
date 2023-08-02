package unit.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidLoginException;
import org.kainos.ea.model.Login;
import org.kainos.ea.validator.AuthValidator;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthValidatorTest {

    Login login;

    AuthDao authDao = Mockito.mock(AuthDao.class);
    AuthValidator authValidator = new AuthValidator(authDao);



    @BeforeEach
    void resetRequests(){
        login = new Login(
                "email@email.com",
                "password"
        );
    }
    @Test
    public void isValidLogin_shouldReturnTrue_whenValidLogin() throws DatabaseConnectionException, SQLException, InvalidLoginException {
        Mockito.when(authDao.register(login)).thenReturn(true);
        assertTrue(authValidator.isValidLogin(login));
    }

    @Test
    public void isValidLogin_shouldThrowIInvalidLoginException_whenPasswordTooLong() {
        login.setPassword("X".repeat(65));
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
    }

    @Test
    public void isValidLogin_shouldThrowIInvalidLoginException_whenPasswordTooShort() {
        login.setPassword("X".repeat(7));
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
    }

    @Test
    public void isValidLogin_shouldThrowInvalidLoginException_whenEmailTooLong() {
        login.setEmail("X".repeat(59)+"@e.com");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
    }

    @Test
    public void isValidLogin_shouldThrowInvalidLoginException_whenEmailInvalid() {
        login.setEmail("xxxx");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("xxxx.com");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("xxxx@");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("@");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("@xxxx");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("xxxx@zxxx");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("xxxx@zxxx.");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("xxxx=@zxxx.com");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
        login.setEmail("xxxx@.com");
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
    }

    @Test
    public void isValidLogin_shouldThrowInvalidLoginException_whenEmailTaken() throws DatabaseConnectionException, SQLException {
        Mockito.when(authDao.isEmailTaken(login.getEmail())).thenReturn(true);
        assertThrows(InvalidLoginException.class, () -> {
            authValidator.isValidLogin(login);
        });
    }
}