package org.kainos.ea.client;

public class TokenExpiredException extends Throwable {
    @Override
    public String getMessage() {
        return "Token expired";
    }
}
