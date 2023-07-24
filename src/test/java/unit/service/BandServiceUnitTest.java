package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.exceptions.FailedToGetBandException;
import org.kainos.ea.models.Band;
import org.kainos.ea.services.BandService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BandServiceUnitTest {
    BandDao bandDao = Mockito.mock(BandDao.class);

    BandService bandService = new BandService(bandDao);

    Band band = new Band(
            1,
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );

    @Test
    void getAllBands_shouldReturnBandList_whenDaoReturnsBandList() throws FailedToGetBandException, SQLException {
        ArrayList<Band> list = new ArrayList<>();
        list.add(band);
        list.add(band);
        list.add(band);

        Mockito.when(bandDao.getAllBands()).thenReturn(list);

        assertEquals(list, bandService.getAllBands());
    }

    @Test
    void getAllBands_shouldThrowFailedToGetBandException_whenDaoThrowsSQLException() throws SQLException{
        Mockito.when(bandDao.getAllBands()).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandException.class, () -> bandService.getAllBands());
    }

    @Test
    void getBandById_shouldReturnBand_whenDaoReturnsBand() throws SQLException, FailedToGetBandException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);

        assertEquals(band, bandService.getBandById(1));
    }

    @Test
    void getBandById_shouldThrowFailedToGetBandException_whenDaoThrowsSQLException() throws SQLException {
        Mockito.when(bandDao.getBandById(1)).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandException.class, () -> bandService.getBandById(1));
    }
}