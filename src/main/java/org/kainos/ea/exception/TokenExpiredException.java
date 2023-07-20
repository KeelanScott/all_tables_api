package org.kainos.ea.exception;

public class TokenExpiredException extends Throwable {
    @Override
    public String getMessage() {
        return "Token expired";
    }
}
