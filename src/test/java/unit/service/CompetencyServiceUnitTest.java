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
    void createBandCompetency_shouldThrowInvalidBandCompetencyException_whenValidatorThrowsInvalidBandCompetencyException() throws InvalidBandCompetencyException {
        BandCompetency bandCompetency = new BandCompetency(
                1,
                1,
                "Description"
        );
        Mockito.when(bandCompetencyValidator.isValidBandCompetency(bandCompetency)).thenThrow(InvalidBandCompetencyException.class);
        assertThrows(InvalidBandCompetencyException.class, () -> competencyService.createBandCompetency(bandCompetency));
    }

    @Test
    void getCompetencyId_shouldThrowCompetencyDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException {
        Mockito.when(competencyDao.getCompetencyById(0)).thenReturn(null);
        assertThrows(CompetencyDoesNotExistException.class, () -> competencyService.getCompetencyById(0));
    }

    @Test
    void getCompetencyById_shouldReturnCompetency_whenDaoReturnsCompetency() throws SQLException, DatabaseConnectionException, CompetencyDoesNotExistException, FailedToGetCompetencyException {
        Mockito.when(competencyDao.getCompetencyById(1)).thenReturn(competency);
        assertEquals(competency, competencyService.getCompetencyById(1));
    }

    @Test
    void getCompetencyById_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(competencyDao.getCompetencyById(1)).thenThrow(SQLException.class);
        assertThrows(FailedToGetCompetencyException.class, () -> competencyService.getCompetencyById(1));
    }
}