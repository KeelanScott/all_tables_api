package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.exceptions.FailedToGetCompetenciesException;
import org.kainos.ea.models.Competency;
import org.kainos.ea.services.CompetencyService;
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
    void getAllCompetencies_shouldReturnCompetencyList_whenDaoReturnsCompetencyList() throws FailedToGetCompetenciesException, SQLException {
        ArrayList<Competency> list = new ArrayList<>();
        list.add(competency);
        list.add(competency);
        list.add(competency);

        Mockito.when(competencyDao.getAllCompetencies()).thenReturn(list);

        assertEquals(list, competencyService.getAllCompetencies());
    }

    @Test
    void getAllCompetencies_shouldThrowFailedToGetCompetencyException_whenDaoThrowsSQLException() throws SQLException{
        Mockito.when(competencyDao.getAllCompetencies()).thenThrow(SQLException.class);

        assertThrows(FailedToGetCompetenciesException.class, () -> competencyService.getAllCompetencies());
    }

}