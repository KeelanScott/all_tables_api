package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetCompetenciesException;
import org.kainos.ea.exception.InvalidBandCompetencyException;
import org.kainos.ea.model.BandCompetency;
import org.kainos.ea.model.Competency;
import org.kainos.ea.service.CompetencyService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CompetencyServiceUnitTest {
    CompetencyDao competencyDao = Mockito.mock(CompetencyDao.class);

    CompetencyService competencyService = new CompetencyService(competencyDao);

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
    void createBandCompetency_shouldThrowInvalidBandCompetencyException_whenValidatorThrowsInvalidBandCompetencyException() {
        BandCompetency bandCompetency = new BandCompetency(
                1,
                1,
                "d"
        );
        assertThrows(InvalidBandCompetencyException.class, () -> competencyService.createBandCompetency(bandCompetency));
    }
}