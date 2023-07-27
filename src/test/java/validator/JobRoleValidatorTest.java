package validator;

import org.junit.jupiter.api.Test;
import org.kainos.ea.dao.BandDao;
import org.kainos.ea.dao.CapabilityDao;
import org.kainos.ea.dao.JobRoleDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.InvalidJobRoleException;
import org.kainos.ea.model.Band;
import org.kainos.ea.model.Capability;
import org.kainos.ea.model.JobRoleRequest;
import org.kainos.ea.validator.JobRoleValidator;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobRoleValidatorTest {

    JobRoleRequest jobRoleRequest = new JobRoleRequest(
            "Software Engineer",
            1,
            1,
            "Software Engineer"
    );

    BandDao bandDao = Mockito.mock(BandDao.class);
    CapabilityDao capabilityDao = Mockito.mock(CapabilityDao.class);

    JobRoleDao jobRoleDao = Mockito.mock(JobRoleDao.class);

    JobRoleValidator jobRoleValidator = new JobRoleValidator(jobRoleDao, bandDao, capabilityDao);

    Band band = new Band(
            1,
            "Band 1",
            "Executive",
            "spec"
    );

    Capability capability = new Capability(
            1,
            "Engineering"
    );


    @Test
    public void isValidJobRole_shouldReturnTrue_whenValidJobRole() throws InvalidJobRoleException, DatabaseConnectionException, SQLException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(band);
        Mockito.when(capabilityDao.getCapabilityById(1)).thenReturn(capability);
        assert(jobRoleValidator.isValidJobRole(jobRoleRequest));
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenNameTooLong() {
        jobRoleRequest.setName("X".repeat(51));
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenNameTooShort() {
        jobRoleRequest.setName("");
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenBandIdSsZeroOrLess() {
        jobRoleRequest.setBandId(0);
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenBandIdIsNotFound() throws DatabaseConnectionException, SQLException {
        Mockito.when(bandDao.getBandById(1)).thenReturn(null);
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenCapabilityIdIsZeroOrLess() {
        jobRoleRequest.setCapabilityId(0);
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenCapabilityIdIsNotFound() throws DatabaseConnectionException, SQLException {
        Mockito.when(capabilityDao.getCapabilityById(1)).thenReturn(null);
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenSpecificationTooLong() {
        jobRoleRequest.setSpecification("X".repeat(256));
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }

    @Test
    public void isValidJobRole_shouldThrowInvalidJobRoleException_whenSpecificationTooShort() {
        jobRoleRequest.setSpecification("");
        assertThrows(InvalidJobRoleException.class, () -> {
            jobRoleValidator.isValidJobRole(jobRoleRequest);
        });
    }
}