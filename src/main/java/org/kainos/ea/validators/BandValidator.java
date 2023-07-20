package org.kainos.ea.validators;

import org.kainos.ea.models.BandRequest;

public class BandValidator {
    public String isValidBand(BandRequest bandRequest) {
        if (bandRequest.getLevel().length() > 30) return "Band level must be under 30 characters";
        if (bandRequest.getName().length() > 30) return "Band name must be under 30 characters";
        if (bandRequest.getResponsibilities().length() > 255) return "Band responsibilities must be under 255 characters";

        if (bandRequest.getName().length() < 1) return "Band must have a name";
        if (bandRequest.getLevel().length() < 1) return "Band must have a level";

        return null;
    }
}
