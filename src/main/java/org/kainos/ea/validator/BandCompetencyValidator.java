package org.kainos.ea.validator;

import org.kainos.ea.exception.InvalidBandCompetencyException;
import org.kainos.ea.model.BandCompetencyRequest;

public class BandCompetencyValidator {
    public boolean isValidBandCompetency(BandCompetencyRequest bandCompetency) throws InvalidBandCompetencyException {
        if (bandCompetency.getDescription().length() > 512) throw new InvalidBandCompetencyException("Description must be under 512 characters");
        if (bandCompetency.getDescription().length() < 5) throw new InvalidBandCompetencyException("Description must be over 5 characters");
        return true;
    }
}
