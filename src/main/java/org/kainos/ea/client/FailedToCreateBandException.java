package org.kainos.ea.client;

public class FailedToCreateBandException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to add band to the database.";
    }
}
