package org.kainos.ea.exception;

public class FailedToVerifyTokenException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to verify token";
    }
}
