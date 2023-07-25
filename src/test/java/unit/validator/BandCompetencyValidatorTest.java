package unit.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.exceptions.InvalidBandCompetencyException;
import org.kainos.ea.models.BandCompetency;
import org.kainos.ea.models.Competency;
import org.kainos.ea.validators.BandCompetencyValidator;

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
