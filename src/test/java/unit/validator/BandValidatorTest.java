package unit.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.BandRequest;
import org.kainos.ea.validator.BandValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandValidatorTest {

    BandValidator bandValidator = new BandValidator();

    BandRequest bandRequest;

    // before each reset bandRequest
    @BeforeEach
    public void resetBandRequest() {
        bandRequest = new BandRequest(
                "Band 1",
                "Executive",
                "Responsibilities"
        );
    }


    @Test
    public void isValidBand_shouldReturnTrue_whenValidBand() throws InvalidBandException {
        assert(bandValidator.isValidBand(bandRequest));
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenLevelTooLong() {
        bandRequest.setLevel("X".repeat(31));
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenNameTooLong() {
        bandRequest.setName("X".repeat(31));
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenResponsibilitiesTooLong() {
        bandRequest.setResponsibilities("X".repeat(256));
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }


    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenLevelTooShort() {
        bandRequest.setLevel("");
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenNameTooShort() {
        bandRequest.setName("");
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

    @Test
    public void isValidBand_shouldThrowInvalidBandException_whenResponsibilitiesTooShort() {
        bandRequest.setResponsibilities("X".repeat(4));
        assertThrows(InvalidBandException.class, () -> {
            bandValidator.isValidBand(bandRequest);
        });
    }

}
