package org.kainos.ea.exception;

public class FailedToCreateBandException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to add band to the database.";
    }
}
