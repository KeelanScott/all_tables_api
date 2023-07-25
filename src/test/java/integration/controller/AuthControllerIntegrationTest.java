package integration.controller;


import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.kainos.ea.model.Login;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthControllerIntegrationTest {

    Login invalidLogin = new Login(
            "admin",
            "admin"
    );

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void login_shouldReturn200_whenSucessfulLogin() {
        Login validLogin = new Login(
                "keelan@gmail.com",
                "Scott"
        );


        Response responseLogin = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(validLogin, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(200, responseLogin.getStatus());
    }

    @Test
    void login_shouldReturn400_whenFailedLogin() {

        Response responseLogin = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(invalidLogin, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, responseLogin.getStatus());
    }

//    @Test
//    void login_shouldReturnToken_whenSuccessfulLogin() {
//        Token expectedToken = new Token("sampleToken");
//        Token responseToken = APP.client().target("http://localhost:8080/api/login")
//                .request()
//                .get(Token.class);
//
//        Assertions.assertEquals(expectedToken, response.getTok());
//    }
}
