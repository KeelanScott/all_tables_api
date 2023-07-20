package org.kainos.ea.exceptions;

public class FailedToGetTrainingCourseException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get training courses from the database.";
    }
}
