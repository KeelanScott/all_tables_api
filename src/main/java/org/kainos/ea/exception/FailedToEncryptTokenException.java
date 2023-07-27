package org.kainos.ea.exception;

public class FailedToEncryptTokenException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to generate token";
    }
}