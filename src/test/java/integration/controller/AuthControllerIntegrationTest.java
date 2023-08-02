package integration.controller;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.dao.AuthDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.model.Login;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthControllerIntegrationTest {

    AuthDao authDao = new AuthDao();
        String email = System.getenv("LOGIN_EMAIL");
        String password = System.getenv("LOGIN_PASSWORD");
    Login invalidLogin = new Login(
            "admin",
            "admin"
    );

    Login validLogin = new Login(
            email,
            password
    );

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void login_shouldReturn200_whenSuccessfulLogin() {
        Response responseLogin = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(validLogin, MediaType.APPLICATION_JSON_TYPE));

        String responseToken = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(validLogin, MediaType.APPLICATION_JSON_TYPE), String.class);

        Assertions.assertEquals(200, responseLogin.getStatus());
        Assertions.assertNotNull(responseToken);
    }

    @Test
    void login_shouldReturn400_whenFailedLogin() {
        Response responseLogin = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, responseLogin.getStatus());
    }

    @Test
    void register_shouldReturn200_whenSuccessfulRegister() throws DatabaseConnectionException, SQLException {
        Login login = new Login(
                "nhdiabeynuansac@ebnnanjkmascwe.com",
                "password"
        );

        Response response = APP.client().target("http://localhost:8080/api/register")
                .request()
                .post(Entity.entity(login, MediaType.APPLICATION_JSON_TYPE));

        authDao.deleteUser(login.getEmail());
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void register_shouldReturn400_whenFailedRegister() {
        Response response = APP.client().target("http://localhost:8080/api/register")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }
}
