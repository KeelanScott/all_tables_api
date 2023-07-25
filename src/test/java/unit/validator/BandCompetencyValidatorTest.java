package unit.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.InvalidBandCompetencyException;
import org.kainos.ea.model.BandCompetency;
import org.kainos.ea.validator.BandCompetencyValidator;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandCompetencyValidatorTest {
    BandCompetencyValidator bandCompetencyValidator = new BandCompetencyValidator();

    BandCompetency bandCompetency;

    // before each reset bandCompetency
    @BeforeEach
    public void resetBandCompetencyRequest() {
        bandCompetency = new BandCompetency(
                1,
                1,
                "Responsibilities"
        );
    }

    @Test
    public void isValidBandCompetency_shouldReturnTrue_whenValidBandCompetency() throws InvalidBandCompetencyException {
        assert(bandCompetencyValidator.isValidBandCompetency(bandCompetency));
    }

    @Test
    public void isValidBandCompetency_shouldThrowInvalidBandCompetencyException_whenDescriptionTooLong() {
        bandCompetency.setDescription("X".repeat(513));
        assertThrows(InvalidBandCompetencyException.class, () -> {
            bandCompetencyValidator.isValidBandCompetency(bandCompetency);
        });
    }

    @Test
    public void isValidBandCompetency_shouldThrowInvalidBandCompetencyException_whenDescriptionTooShort() {
        bandCompetency.setDescription("X".repeat(4));
        assertThrows(InvalidBandCompetencyException.class, () -> {
            bandCompetencyValidator.isValidBandCompetency(bandCompetency);
        });
    }
}
