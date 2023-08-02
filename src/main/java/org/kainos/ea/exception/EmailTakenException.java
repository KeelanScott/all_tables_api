package org.kainos.ea.exception;

public class EmailTakenException extends Exception {
    @Override
    public String getMessage() {
        return "Email taken";
    }
}
