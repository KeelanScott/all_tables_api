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
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BandServiceUnitTest {
    BandDao bandDao = Mockito.mock(BandDao.class);
    CompetencyDao competencyDao = Mockito.mock(CompetencyDao.class);
    TrainingCourseDao trainingCourseDao = Mockito.mock(TrainingCourseDao.class);
    BandValidator bandValidator = Mockito.mock(BandValidator.class);
    BandCompetencyValidator bandCompetencyValidator = Mockito.mock(BandCompetencyValidator.class);

    BandService bandService = new BandService(bandDao, competencyDao, trainingCourseDao, bandValidator, bandCompetencyValidator);

    Band band = new Band(
            1,
            "Band 1",
            "Executive",
            "These are the responsibilities"
    );

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
    List<BandCompetency> bandCompetencyList = List.of(
            new BandCompetency(1, 1, "Description"),
            new BandCompetency(1, 1, "Description")
    );
    int[] trainingCourses = { 1, 1 };
    List<Integer> trainingCourseList = List.of(1, 1);

    BandWithDetailsRequest bandWithDetailsRequest = new BandWithDetailsRequest(bandRequest, competencies, trainingCourses);

    @Test
    void createBand_shouldReturnId_whenDaoReturnsId() throws SQLException, InvalidBandException, FailedToCreateBandException, DatabaseConnectionException, InvalidBandCompetencyException, FailedToCreateBandCompetencyException, FailedToCreateBandTrainingCourseException {
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.areValidBandCompetencies(competencies, bandCompetencyValidator)).thenReturn(true);
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

    @Test
    void createBand_shouldThrowInvalidBandCompetencyException_whenValidatorThrowsInvalidBandCompetencyException() throws InvalidBandCompetencyException {
        Mockito.when(bandCompetencyValidator.areValidBandCompetencies(competencies, bandCompetencyValidator)).thenThrow(InvalidBandCompetencyException.class);
        assertThrows(InvalidBandCompetencyException.class, () -> bandService.createBand(bandWithDetailsRequest));
    }

    @Test
    void createBand_shouldThrowFailedToCreateBandException_whenBandIsNotCreated() throws DatabaseConnectionException, SQLException, InvalidBandException, InvalidBandCompetencyException {
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.areValidBandCompetencies(competencies, bandCompetencyValidator)).thenReturn(true);
        Mockito.when(bandDao.createBand(bandRequest)).thenReturn(-1);

        assertThrows(FailedToCreateBandException.class, () -> bandService.createBand(bandWithDetailsRequest));
    }

    @Test
    void createBand_shouldThrowFailedToCreateBandCompetencyException_whenBandIsNotCreated() throws DatabaseConnectionException, SQLException, InvalidBandException, InvalidBandCompetencyException {
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.areValidBandCompetencies(competencies, bandCompetencyValidator)).thenReturn(true);
        Mockito.when(bandDao.createBand(bandRequest)).thenReturn(1);
        Mockito.when(competencyDao.createBandCompetency(competency, 1)).thenReturn(-1);

        assertThrows(FailedToCreateBandCompetencyException.class, () -> bandService.createBand(bandWithDetailsRequest));
    }

    @Test
    void createBand_shouldThrowFailedToCreateBandTrainingCourseException_whenBandIsNotCreated() throws DatabaseConnectionException, SQLException, InvalidBandException, InvalidBandCompetencyException {
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.areValidBandCompetencies(competencies, bandCompetencyValidator)).thenReturn(true);
        Mockito.when(bandDao.createBand(bandRequest)).thenReturn(1);
        Mockito.when(competencyDao.createBandCompetency(competency, 1)).thenReturn(1);
        Mockito.when(trainingCourseDao.createBandTrainingCourse(1, 1)).thenReturn(-1);

        assertThrows(FailedToCreateBandTrainingCourseException.class, () -> bandService.createBand(bandWithDetailsRequest));
    }

    @Test
    void getBandById_shouldReturnBand_whenDaoReturnsBand() throws SQLException, FailedToGetBandException, DatabaseConnectionException, BandDoesNotExistException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(competencyDao.getBandCompetencies(1)).thenReturn(bandCompetencyList);
        Mockito.when(trainingCourseDao.getTrainingForBand(1)).thenReturn(trainingCourseList);

        BandWithDetailsResponse response = new BandWithDetailsResponse(band, bandCompetencyList, trainingCourseList);

        assertEquals(response, bandService.getBandById(1));
    }

    @Test
    void getBandById_shouldThrowFailedToGetBandException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenThrow(SQLException.class);
        assertThrows(FailedToGetBandException.class, () -> bandService.getBandById(1));
    }

    @Test
    void getBandById_shouldThrowFailedToGetBandException_whenDaoThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenThrow(DatabaseConnectionException.class);
        assertThrows(FailedToGetBandException.class, () -> bandService.getBandById(1));
    }

    @Test
    void getBandById_shouldThrowBandDoesNotExistException_whenBandDoesNotExist() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(null);
        assertThrows(BandDoesNotExistException.class, () -> bandService.getBandById(1));
    }

    @Test
    void updateBand_shouldReturnOne_whenBandIsUpdated() throws SQLException, DatabaseConnectionException, FailedToUpdateBandException, InvalidBandException, InvalidBandCompetencyException, FailedToUpdateBandCompetencyException, FailedToUpdateBandTrainingCourseException, BandDoesNotExistException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.isValidBandCompetency(competency)).thenReturn(true);
        Mockito.when(bandDao.updateBand(1, bandRequest)).thenReturn(1);
        Mockito.when(competencyDao.deleteBandCompetencies(1)).thenReturn(1);
        Mockito.when(competencyDao.createBandCompetency(competency, 1)).thenReturn(1);
        Mockito.when(trainingCourseDao.deleteBandTrainingCourses(1)).thenReturn(1);
        Mockito.when(trainingCourseDao.createBandTrainingCourse(1, 1)).thenReturn(1);

        assertDoesNotThrow(() -> bandService.updateBand(1, bandWithDetailsRequest));
    }

    @Test
    void updateBand_shouldThrowBandDoesNotExistException_whenBandDoesNotExist() throws SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(null);
        
        assertThrows(BandDoesNotExistException.class, () -> bandService.updateBand(1, bandWithDetailsRequest));
    }

    @Test
    void updateBand_shouldThrowInvalidBandException_whenBandValidatorThrowsInvalidBandException() throws InvalidBandException, InvalidBandCompetencyException, FailedToUpdateBandException, SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenThrow(InvalidBandException.class);

        assertThrows(InvalidBandException.class, () -> bandService.updateBand(1, bandWithDetailsRequest));
    }

    @Test
    void updateBand_shouldThrowInvalidBandCompetencyException_whenBandCompetencyValidatorThrowsInvalidBandCompetencyException() throws InvalidBandException, InvalidBandCompetencyException, FailedToUpdateBandException, SQLException, DatabaseConnectionException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.isValidBandCompetency(competency)).thenThrow(InvalidBandCompetencyException.class);

        assertThrows(InvalidBandCompetencyException.class, () -> bandService.updateBand(1, bandWithDetailsRequest));
    }

    @Test
    void updateBand_shouldThrowFailedToUpdateBandException_whenBandIsNotUpdated() throws SQLException, DatabaseConnectionException, InvalidBandException, InvalidBandCompetencyException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.isValidBandCompetency(competency)).thenReturn(true);
        Mockito.when(bandDao.updateBand(1, bandRequest)).thenReturn(-1);

        assertThrows(FailedToUpdateBandException.class, () -> bandService.updateBand(1, bandWithDetailsRequest));
    }

    @Test
    void updateBand_shouldThrowFailedToUpdateBandCompetencyException_whenBandCompetencyIsNotUpdated() throws SQLException, DatabaseConnectionException, InvalidBandException, InvalidBandCompetencyException, FailedToUpdateBandException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.isValidBandCompetency(competency)).thenReturn(true);
        Mockito.when(bandDao.updateBand(1, bandRequest)).thenReturn(1);
        Mockito.when(competencyDao.deleteBandCompetencies(1)).thenReturn(1);
        Mockito.when(competencyDao.createBandCompetency(competency, 1)).thenReturn(-1);

        assertThrows(FailedToUpdateBandCompetencyException.class, () -> bandService.updateBand(1, bandWithDetailsRequest));
    }

    @Test
    void updateBand_shouldThrowFailedToUpdateBandTrainingCourseException_whenBandTrainingCourseIsNotUpdated() throws SQLException, DatabaseConnectionException, InvalidBandException, InvalidBandCompetencyException, FailedToUpdateBandException, FailedToUpdateBandCompetencyException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(bandValidator.isValidBand(bandRequest)).thenReturn(true);
        Mockito.when(bandCompetencyValidator.isValidBandCompetency(competency)).thenReturn(true);
        Mockito.when(bandDao.updateBand(1, bandRequest)).thenReturn(1);
        Mockito.when(competencyDao.deleteBandCompetencies(1)).thenReturn(1);
        Mockito.when(competencyDao.createBandCompetency(competency, 1)).thenReturn(1);
        Mockito.when(trainingCourseDao.deleteBandTrainingCourses(1)).thenReturn(1);
        Mockito.when(trainingCourseDao.createBandTrainingCourse(1, 1)).thenReturn(-1);

        assertThrows(FailedToUpdateBandTrainingCourseException.class, () -> bandService.updateBand(1, bandWithDetailsRequest));
    }
}