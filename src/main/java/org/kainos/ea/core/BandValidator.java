package org.kainos.ea.core;

import org.kainos.ea.cli.BandRequest;

public class BandValidator {
    public String isValidBand(BandRequest bandRequest) {
        if (bandRequest.getLevelId() < 0) return "Band level must be more than 0";
        if (bandRequest.getName().length() > 20) return "Band name must be under 20 characters";
        if (bandRequest.getName().length() < 1) return "Band must have a name";

        return null;
    }
}
