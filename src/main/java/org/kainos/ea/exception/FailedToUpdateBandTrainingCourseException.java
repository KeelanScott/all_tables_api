package org.kainos.ea.exception;

public class FailedToUpdateBandTrainingCourseException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to update band training course in the database.";
    }
}
