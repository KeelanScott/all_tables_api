package org.kainos.ea.exception;

public class FailedToCreateBandTrainingCourseException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to add band training course to the database.";
    }
}
