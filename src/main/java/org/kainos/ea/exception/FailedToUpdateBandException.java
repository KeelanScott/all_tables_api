package org.kainos.ea.exception;

public class FailedToUpdateBandException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to update band in the database.";
    }
}
