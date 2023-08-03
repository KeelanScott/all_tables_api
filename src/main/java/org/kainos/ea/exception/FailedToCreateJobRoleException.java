package org.kainos.ea.exception;

public class FailedToCreateJobRoleException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to create job role";
    }

}
