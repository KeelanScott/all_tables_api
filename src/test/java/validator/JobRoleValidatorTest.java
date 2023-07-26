package validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.validator.JobRoleValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleValidatorTest {

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Software Engineer",
            1,
            1,
            "Software Engineer"
    );

    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() throws InvalidJobRoleException {
        assert(JobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenNameTooLong() {
        jobRoleRequest.setName("X".repeat(51));
        assertThrows(InvalidJobRoleException.class, () -> {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenNameTooShort() {
        jobRoleRequest.setName("");
        assertThrows(InvalidJobRoleException.class, () -> {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenBandIdSsZeroOrLess() {
        jobRoleRequest.setBandId(0);
        assertThrows(InvalidJobRoleException.class, () -> {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenBandIdIsNotFound() {
        jobRoleRequest.setBandId(1111111111);
        assertThrows(InvalidJobRoleException.class, () -> {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenCapabilityIdIsZeroOrLess() {
        jobRoleRequest.setCapabilityId(0);
        assertThrows(InvalidJobRoleException.class, () -> {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenCapabilityIdIsNotFound() {
        jobRoleRequest.setCapabilityId(1111111111);
        assertThrows(InvalidJobRoleException.class, () -> {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenSpecificationTooLong() {
        jobRoleRequest.setSpecification("X".repeat(256));
        assertThrows(InvalidJobRoleException.class, () -> {
            JobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }
}
