package org.kainos.ea.validators;

import org.kainos.ea.exceptions.InvalidBandCompetencyException;
import org.kainos.ea.models.BandCompetency;

public class BandCompetencyValidator {
    public boolean isValidBandCompetency(BandCompetency bandCompetency) throws InvalidBandCompetencyException {
        if (bandCompetency.getDescription().length() > 512) throw new InvalidBandCompetencyException("Description must be under 512 characters");
        if (bandCompetency.getDescription().length() < 5) throw new InvalidBandCompetencyException("Description must be over 5 characters");
        return true;
    }
}
