package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetCapabilityException;
import org.kainos.ea.model.Capability;
import org.kainos.ea.service.CapabilityService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CapabilityServiceTest {
    CapabilityDao capabilityDao = Mockito.mock(CapabilityDao.class);

    CapabilityService capabilityService = new CapabilityService(capabilityDao);

    Capability capability = new Capability(
            1,
            "Engineering",
            "description"
    );

    @Test
    void getAllCapabilities_shouldReturnCapabilityList_whenDaoReturnsCapabilityList() throws SQLException, FailedToGetCapabilityException, DatabaseConnectionException {
        ArrayList<Capability> list = new ArrayList<>();
        list.add(capability);
        list.add(capability);
        list.add(capability);

        Mockito.when(capabilityDao.getAllCapabilities()).thenReturn(list);

        assertEquals(list, capabilityService.getAllCapabilities());
    }

    @Test
    void getAllCapabilities_shouldThrowFailedToGetCapabilityException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(capabilityDao.getAllCapabilities()).thenThrow(SQLException.class);

        assertThrows(FailedToGetCapabilityException.class, () -> capabilityService.getAllCapabilities());
    }
}