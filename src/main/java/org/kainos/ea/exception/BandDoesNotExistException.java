package org.kainos.ea.exception;

public class BandDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Capability does not exist";
    }
}
