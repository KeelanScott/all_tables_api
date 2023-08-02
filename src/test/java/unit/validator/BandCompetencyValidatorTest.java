package unit.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.CompetencyDoesNotExistException;
import org.kainos.ea.exception.FailedToGetCompetencyException;
import org.kainos.ea.exception.InvalidBandCompetencyException;
import org.kainos.ea.model.BandCompetencyRequest;
import org.kainos.ea.model.Competency;
import org.kainos.ea.service.CompetencyService;
import org.kainos.ea.validator.BandCompetencyValidator;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandCompetencyValidatorTest {
    CompetencyService competencyService = Mockito.mock(CompetencyService.class);
    BandCompetencyValidator bandCompetencyValidator = new BandCompetencyValidator(competencyService);
    BandCompetencyValidator mockBandCompetencyValidator = Mockito.mock(BandCompetencyValidator.class);

    Competency competency = new Competency(
            1,
            "Competency 1"
    );

    @Test
    public void isValidBandCompetency_shouldReturnTrue_whenValidBandCompetency() throws InvalidBandCompetencyException, CompetencyDoesNotExistException, FailedToGetCompetencyException {
        Mockito.when(competencyService.getCompetencyById(1)).thenReturn(competency);
        BandCompetencyRequest bandCompetencyRequest = new BandCompetencyRequest(
                1,
                "Responsibilities"
        );
        assert(bandCompetencyValidator.isValidBandCompetency(bandCompetencyRequest));
    }

    @Test
    public void isValidBandCompetency_shouldThrowInvalidBandCompetencyException_whenDescriptionTooLong() {
        BandCompetencyRequest bandCompetencyRequest = new BandCompetencyRequest(
                1,
                "X".repeat(513)
        );
        assertThrows(InvalidBandCompetencyException.class, () -> {
            bandCompetencyValidator.isValidBandCompetency(bandCompetencyRequest);
        });
    }

    @Test
    public void isValidBandCompetency_shouldThrowInvalidBandCompetencyException_whenDescriptionTooShort() {
        BandCompetencyRequest bandCompetencyRequest = new BandCompetencyRequest(
                1,
                "X".repeat(4)
        );
        assertThrows(InvalidBandCompetencyException.class, () -> {
            bandCompetencyValidator.isValidBandCompetency(bandCompetencyRequest);
        });
    }

    @Test
    public void isValidBandCompetency_shouldThrowInvalidBandCompetencyException_whenCompetencyDoesNotExist() throws FailedToGetCompetencyException, CompetencyDoesNotExistException {
        Mockito.when(competencyService.getCompetencyById(1)).thenThrow(CompetencyDoesNotExistException.class);
        BandCompetencyRequest bandCompetencyRequest = new BandCompetencyRequest(
                1,
                "Responsibilities"
        );
        assertThrows(InvalidBandCompetencyException.class, () -> {
            bandCompetencyValidator.isValidBandCompetency(bandCompetencyRequest);
        });
    }

    @Test
    public void isValidBandCompetency_shouldThrowInvalidBandCompetencyException_whenFailedToGetCompetency() throws FailedToGetCompetencyException, CompetencyDoesNotExistException {
        Mockito.when(competencyService.getCompetencyById(1)).thenThrow(FailedToGetCompetencyException.class);
        BandCompetencyRequest bandCompetencyRequest = new BandCompetencyRequest(
                1,
                "Responsibilities"
        );
        assertThrows(InvalidBandCompetencyException.class, () -> {
            bandCompetencyValidator.isValidBandCompetency(bandCompetencyRequest);
        });
    }

    @Test
    public void areValidBandCompetencies_shouldReturnTrue_whenIsValidBandCompetencyReturnsTrue() throws InvalidBandCompetencyException {
        BandCompetencyRequest  bandCompetencyRequest = new BandCompetencyRequest(
                1,
                "Responsibilities"
        );
        Mockito.when(mockBandCompetencyValidator.isValidBandCompetency(bandCompetencyRequest)).thenReturn(true);
        BandCompetencyRequest[] bandCompetencyRequests = new BandCompetencyRequest[] {
                bandCompetencyRequest, bandCompetencyRequest
        };
        assert(bandCompetencyValidator.areValidBandCompetencies(bandCompetencyRequests, mockBandCompetencyValidator));
    }

    @Test
    public void areValidBandCompetencies_shouldThrowInvalidBandCompetencyException_whenIsValidBandCompetencyThrowsInvalidBandCompetencyException() throws InvalidBandCompetencyException {
        BandCompetencyRequest  bandCompetencyRequest = new BandCompetencyRequest(
                1,
                "Responsibilities"
        );
        Mockito.when(mockBandCompetencyValidator.isValidBandCompetency(bandCompetencyRequest)).thenThrow(InvalidBandCompetencyException.class);
        BandCompetencyRequest[] bandCompetencyRequests = new BandCompetencyRequest[] {
                bandCompetencyRequest, bandCompetencyRequest
        };
        assertThrows(InvalidBandCompetencyException.class, () -> {
            bandCompetencyValidator.areValidBandCompetencies(bandCompetencyRequests, mockBandCompetencyValidator);
        });
    }
}
