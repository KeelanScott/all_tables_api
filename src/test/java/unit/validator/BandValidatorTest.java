package unit.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.validator.BandValidator;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandValidatorTest {

    BandValidator bandValidator = new BandValidator();

    @Test
    public void isValidBand_shouldReturnTrue_whenValidBand() throws InvalidBandException {
        BandRequest bandRequest = new BandRequest(
                "Band 1",
                "Executive",
                "Responsibilities"
        );
        assert(bandValidator.isValidBand(bandRequest));
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenLevelTooLong() {
        BandRequest bandRequest = new BandRequest(
                "Band 1",
                "X".repeat(31),
                "Responsibilities"
        );
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenNameTooLong() {
        BandRequest bandRequest = new BandRequest(
                "X".repeat(31),
                "Executive",
                "Responsibilities"
        );
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenResponsibilitiesTooLong() {
        BandRequest bandRequest = new BandRequest(
                "Band 1",
                "Executive",
                "X".repeat(256)
        );
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }


    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenLevelTooShort() {
        BandRequest bandRequest = new BandRequest(
                "Band 1",
                "",
                "Responsibilities"
        );
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenNameTooShort() {
        BandRequest bandRequest = new BandRequest(
                "",
                "Executive",
                "Responsibilities"
        );
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenResponsibilitiesTooShort() {
        BandRequest bandRequest = new BandRequest(
                "Band 1",
                "Executive",
                "X".repeat(4)
        );
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }
}
