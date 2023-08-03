package org.kainos.ea.exception;

public class BandDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Band does not exist";
    }
}
