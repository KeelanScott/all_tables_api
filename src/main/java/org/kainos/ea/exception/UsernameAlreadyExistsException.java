package org.kainos.ea.exception;

public class UsernameAlreadyExistsException extends Exception {
    @Override
    public String getMessage() {
        return "Username already exists";
    }
}
