package org.kainos.ea.exception;

public class FailedToGetBandException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get band from the database.";
    }
}