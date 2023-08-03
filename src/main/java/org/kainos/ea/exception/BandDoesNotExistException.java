package org.kainos.ea.exception;

public class BandDoesNotExistException extends Exception {
    @Override
    public String getMessage(){
        return "Band does not exist";
    }
}
