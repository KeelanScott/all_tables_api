package org.kainos.ea.db;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.all_tables_apiApplication;
import org.kainos.ea.all_tables_apiConfiguration;
import org.mockito.Mockito;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthDaoTest {

    static final DropwizardAppExtension<all_tables_apiConfiguration> APP = new DropwizardAppExtension<>(
            all_tables_apiApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

   //// AuthDao authDao = Mockito.mock(AuthDao.class);
 //   DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

//    @Test
//    public void isValidLogin_shouldReturnFalse() throws SQLException, DatabaseConnectionException {
//        Login login = new Login(
//                "Software",
//                "hello"
//        );
//        //Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
//        //Mockito.when(authDao.validLogin(login)).thenReturn();
//
//        Assertions.assertNull(authDao.validLogin(login));
//    }
}
