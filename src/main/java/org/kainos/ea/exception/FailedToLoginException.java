package org.kainos.ea.exception;

public class FailedToLoginException extends Throwable{
    @Override
    public String getMessage() {
        return "Failed to login";
    }
}
