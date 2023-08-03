package org.kainos.ea.validator;

import org.kainos.ea.exception.InvalidBandException;
import org.kainos.ea.model.BandRequest;

public class BandValidator {
    public boolean isValidBand(BandRequest bandRequest) throws InvalidBandException {
        if (bandRequest.getLevel().length() > 30) throw new InvalidBandException("Band level must be under 30 characters");
        if (bandRequest.getName().length() > 30) throw new InvalidBandException("Band name must be under 30 characters");
        if (bandRequest.getResponsibilities().length() > 255) throw new InvalidBandException("Band responsibilities must be under 255 characters");
        if (bandRequest.getName().length() < 1) throw new InvalidBandException("Band must have a name");
        if (bandRequest.getLevel().length() < 1) throw new InvalidBandException("Band must have a level");
        if (bandRequest.getResponsibilities().length() < 5) throw new InvalidBandException("Band responsibilities must be over 5 characters");

        return true;
    }
}
