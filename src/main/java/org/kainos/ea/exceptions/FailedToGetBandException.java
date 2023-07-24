package org.kainos.ea.exceptions;

public class FailedToGetBandException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get band from the database.";
    }
}
