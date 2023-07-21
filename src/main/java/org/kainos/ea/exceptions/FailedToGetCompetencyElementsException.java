package org.kainos.ea.exceptions;

public class FailedToGetCompetencyElementsException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get competency elements from the database.";
    }
}