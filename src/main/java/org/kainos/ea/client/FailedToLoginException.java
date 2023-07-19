package org.kainos.ea.client;

public class FailedToLoginException extends Throwable{
    @Override
    public String getMessage() {
        return "Failed to login";
    }
}
