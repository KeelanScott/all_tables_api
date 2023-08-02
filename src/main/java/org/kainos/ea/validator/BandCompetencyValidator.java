package org.kainos.ea.validator;

import org.kainos.ea.dao.CompetencyDao;
import org.kainos.ea.exception.CompetencyDoesNotExistException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.FailedToGetCompetencyException;
import org.kainos.ea.exception.InvalidBandCompetencyException;
import org.kainos.ea.model.BandCompetencyRequest;
import org.kainos.ea.service.CompetencyService;

public class BandCompetencyValidator {
    private CompetencyService competencyService;

    public BandCompetencyValidator(CompetencyService competencyService) {
        this.competencyService = competencyService;
    }

    public boolean isValidBandCompetency(BandCompetencyRequest bandCompetency) throws InvalidBandCompetencyException {
        if (bandCompetency.getDescription().length() > 512) throw new InvalidBandCompetencyException("Description must be under 512 characters");
        if (bandCompetency.getDescription().length() < 5) throw new InvalidBandCompetencyException("Description must be over 5 characters");

        try {
            competencyService.getCompetencyById(bandCompetency.getCompetencyID());
        } catch (FailedToGetCompetencyException e) {
            throw new InvalidBandCompetencyException("Failed to get competency");
        } catch (CompetencyDoesNotExistException e) {
            throw new InvalidBandCompetencyException("Competency does not exist");
        }

        return true;
    }

    public boolean areValidBandCompetencies(BandCompetencyRequest[] bandCompetencies, BandCompetencyValidator validator) throws InvalidBandCompetencyException {
        for (BandCompetencyRequest bandCompetency : bandCompetencies) {
            validator.isValidBandCompetency(bandCompetency);
        }

        return true;
    }
}
