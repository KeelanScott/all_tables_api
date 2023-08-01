package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.BandCompetency;
import org.kainos.ea.model.Competency;
import org.kainos.ea.service.CompetencyService;
import org.kainos.ea.validator.BandCompetencyValidator;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CompetencyServiceUnitTest {
    CompetencyDao competencyDao = Mockito.mock(CompetencyDao.class);
    BandCompetencyValidator bandCompetencyValidator = Mockito.mock(BandCompetencyValidator.class);

    CompetencyService competencyService = new CompetencyService(competencyDao, bandCompetencyValidator);

    Competency competency = new Competency(
            1,
            "Competency 1"
    );

    BandCompetency bandCompetency = new BandCompetency(
            1,
            1,
            "description"
    );

    @Test
    void getAllCompetencies_shouldReturnCompetencyList_whenDaoReturnsCompetencyList() throws FailedToGetCompetenciesException, SQLException, DatabaseConnectionException {
        ArrayList<Competency> list = new ArrayList<>();
        list.add(competency);
        list.add(competency);
        list.add(competency);

        Mockito.when(competencyDao.getAllCompetencies()).thenReturn(list);

        assertEquals(list, competencyService.getAllCompetencies());
    }

    @Test
    void getAllCompetencies_shouldThrowFailedToGetCompetencyException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(competencyDao.getAllCompetencies()).thenThrow(SQLException.class);

        assertThrows(FailedToGetCompetenciesException.class, () -> competencyService.getAllCompetencies());
    }

    @Test
    void getAllCompetencies_shouldThrowFailedToGetCompetencyException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(competencyDao.getAllCompetencies()).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetCompetenciesException.class, () -> competencyService.getAllCompetencies());
    }

    @Test
    void getBandCompetencies_shouldReturnBandCompetencyList_whenDaoReturnsBandCompetencyList() throws FailedToGetBandCompetenciesException, SQLException, DatabaseConnectionException {
        ArrayList<BandCompetency> list = new ArrayList<>();
        list.add(bandCompetency);
        list.add(bandCompetency);
        list.add(bandCompetency);

        Mockito.when(competencyDao.getBandCompetencies(1)).thenReturn(list);

        assertEquals(list, competencyService.getBandCompetencies(1));
    }

    @Test
    void getBandCompetencies_shouldThrowFailedToGetBandCompetenciesException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(competencyDao.getBandCompetencies(1)).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandCompetenciesException.class, () -> competencyService.getBandCompetencies(1));
    }

    @Test
    void getBandCompetencies_shouldThrowFailedToGetBandCompetenciesException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(competencyDao.getBandCompetencies(1)).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToGetBandCompetenciesException.class, () -> competencyService.getBandCompetencies(1));
    }
}