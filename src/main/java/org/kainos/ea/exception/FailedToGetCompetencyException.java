package org.kainos.ea.exception;

public class FailedToGetCompetencyException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to get competency from the database.";
    }
}
