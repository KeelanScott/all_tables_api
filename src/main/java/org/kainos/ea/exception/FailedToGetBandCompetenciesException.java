package org.kainos.ea.exception;

public class FailedToGetBandCompetenciesException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get band competencies from the database.";
    }
}
