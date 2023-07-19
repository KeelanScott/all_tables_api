package org.kainos.ea.exception;

public class JobRoleNameTooLongException extends Throwable {

    @Override
    public String getMessage(){
        return "Job role name must be less than 50 characters";
    }
}
