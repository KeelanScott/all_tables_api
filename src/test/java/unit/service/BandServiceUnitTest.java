package unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.dao.TrainingCourseDao;
import org.kainos.ea.exception.*;
import org.kainos.ea.model.*;
import org.kainos.ea.service.BandService;
import org.kainos.ea.validator.BandCompetencyValidator;
import org.kainos.ea.validator.BandValidator;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BandServiceUnitTest {
    BandDao bandDao = Mockito.mock(BandDao.class);
    CompetencyDao competencyDao = Mockito.mock(CompetencyDao.class);
    TrainingCourseDao trainingCourseDao = Mockito.mock(TrainingCourseDao.class);
    BandValidator bandValidator = Mockito.mock(BandValidator.class);
    BandCompetencyValidator bandCompetencyValidator = Mockito.mock(BandCompetencyValidator.class);

    BandService bandService = new BandService(bandDao, competencyDao, trainingCourseDao, bandValidator, bandCompetencyValidator);

    BandRequest bandRequest = new BandRequest(
            "Band 1",
            "Executive",
            "these are the responsibilities"
    );

    BandCompetencyRequest competency = new BandCompetencyRequest(
            1,
            "Description"
    );

    BandCompetencyRequest[] competencies = { competency, competency };
    int[] trainingCourses = { 1, 1 };

    BandWithDetailsRequest bandWithDetailsRequest = new BandWithDetailsRequest(bandRequest, competencies, trainingCourses);

    @Test
    void createBand_shouldReturnId_whenDaoReturnsId() throws SQLException, InvalidBandException, FailedToCreateBandException, DatabaseConnectionException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandDao.createBand(bandRequest)).thenReturn(1);
        Mockito.when(competencyDao.createBandCompetency(competency, 1)).thenReturn(1);
        Mockito.when(trainingCourseDao.createBandTrainingCourse(1, 1)).thenReturn(1);

        assertEquals(1, bandService.createBand(bandWithDetailsRequest));
    }

    @Test
    void createBand_shouldThrowFailedToCreateBandException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.createBand(bandRequest)).thenThrow(DatabaseConnectionException.class);

        assertThrows(FailedToCreateBandException.class, () -> bandService.createBand(bandWithDetailsRequest));
    }

    @Test
    void createBand_shouldThrowFailedToCreateBandException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.createBand(bandRequest)).thenThrow(SQLException.class);

        assertThrows(FailedToCreateBandException.class, () -> bandService.createBand(bandWithDetailsRequest));
    }

    @Test
    void createBand_shouldThrowInvalidBandException_whenValidatorThrowsInvalidBandException() throws InvalidBandException {
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenThrow(InvalidBandException.class);
        assertThrows(InvalidBandException.class, () -> bandService.createBand(bandWithDetailsRequest));
    }
}