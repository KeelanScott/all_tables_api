package org.kainos.ea.exception;

public class CompetencyDoesNotExistException extends Exception {
    @Override
    public String getMessage(){
        return "Competency does not exist";
    }
}
