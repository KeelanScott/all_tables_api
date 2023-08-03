package org.kainos.ea.exception;

public class FailedToRegisterException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to register";
    }
}
