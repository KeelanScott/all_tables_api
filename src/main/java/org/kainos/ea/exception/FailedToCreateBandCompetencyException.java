package org.kainos.ea.exception;

public class FailedToCreateBandCompetencyException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to add band competency to the database.";
    }
}
