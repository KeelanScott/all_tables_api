package unit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToCreateBandException;
import org.kainos.ea.exception.FailedToGetBandException;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.service.BandService;
import org.kainos.ea.validator.BandValidator;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BandServiceUnitTest {
    BandDao bandDao = Mockito.mock(BandDao.class);
    BandValidator bandValidator = Mockito.mock(BandValidator.class);

    BandService bandService = new BandService(bandDao, bandValidator);

    Band band = new Band(
            1,
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );

    BandRequest bandRequest = new BandRequest(
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );

    @Test
    void getAllBands_shouldReturnBandList_whenDaoReturnsBandList() throws FailedToGetBandException, SQLException, DatabaseConnectionException {
        ArrayList<Band> list = new ArrayList<>();
        list.add(band);
        list.add(band);
        list.add(band);

        Mockito.when(bandDao.getAllBands()).thenReturn(list);

        assertEquals(list, bandService.getAllBands());
    }

    @Test
    void getAllBands_shouldThrowFailedToGetBandException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getAllBands()).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandException.class, () -> bandService.getAllBands());
    }

    @Test
    void getBandById_shouldReturnBand_whenDaoReturnsBand() throws SQLException, FailedToGetBandException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);

        assertEquals(band, bandService.getBandById(1));
    }

    @Test
    void getBandById_shouldThrowFailedToGetBandException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandException.class, () -> bandService.getBandById(1));
    }

    @Test
    void createBand_shouldReturnId_whenDaoReturnsId() throws SQLException, InvalidBandException, FailedToCreateBandException, DatabaseConnectionException {
        Mockito.when(bandDao.createBand(bandRequest)).thenReturn(1);

        assertEquals(1, bandService.createBand(bandRequest));
    }

    @Test
    void createBand_shouldThrowFailedToCreateBandException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.createBand(bandRequest)).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToCreateBandException.class, () -> bandService.createBand(bandRequest));
    }

    @Test
    void createBand_shouldThrowFailedToCreateBandException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.createBand(bandRequest)).thenThrow(SQLException.class);

        assertThrows(FailedToCreateBandException.class, () -> bandService.createBand(bandRequest));
    }

    @Test
    void createBand_shouldThrowInvalidBandException_whenValidatorThrowsInvalidBandException() throws InvalidBandException {
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenThrow(InvalidBandException.class);
        assertThrows(InvalidBandException.class, () -> bandService.createBand(bandRequest));
    }
}