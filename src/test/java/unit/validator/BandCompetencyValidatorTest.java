package unit.validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.InvalidBandCompetencyException;
import org.kainos.ea.model.BandCompetencyRequest;
import org.kainos.ea.validator.BandCompetencyValidator;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandCompetencyValidatorTest {
    BandCompetencyValidator bandCompetencyValidator = new BandCompetencyValidator();

    @Test
    public void isValidBandCompetency_shouldReturnTrue_whenValidBandCompetency() throws InvalidBandCompetencyException {
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
}
