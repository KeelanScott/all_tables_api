package org.kainos.ea.exception;

public class FailedToUpdateBandCompetencyException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to update band competency in the database.";
    }
}
