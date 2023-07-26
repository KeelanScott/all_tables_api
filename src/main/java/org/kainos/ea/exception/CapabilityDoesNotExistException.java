package org.kainos.ea.exception;

public class CapabilityDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Capability does not exist";
    }
}
