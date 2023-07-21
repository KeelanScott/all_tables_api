package org.kainos.ea.validators;

import org.kainos.ea.models.BandCompetency;

public class BandCompetencyValidator {
    public String isValidBandCompetency(BandCompetency bandCompetency) {
        if (bandCompetency.getDescription().length() > 512) return "Description must be under 512 characters";

        return null;
    }
}
