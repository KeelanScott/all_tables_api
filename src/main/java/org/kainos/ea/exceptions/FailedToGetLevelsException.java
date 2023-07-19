package org.kainos.ea.exceptions;

public class FailedToGetLevelsException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get levels from the database.";
    }
}
