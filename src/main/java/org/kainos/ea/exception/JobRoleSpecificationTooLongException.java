package org.kainos.ea.exception;

public class JobRoleSpecificationTooLongException extends Throwable {

    @Override
    public String getMessage(){
        return "Job role specification must be less than 255 characters";
    }
}
