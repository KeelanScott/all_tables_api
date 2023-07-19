package org.kainos.ea.exceptions;

public class FailedToGetCompetenciesException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get competencies from the database.";
    }
}
