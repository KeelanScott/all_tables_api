package org.kainos.ea.exception;

public class FailedToGetTrainingCoursesException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get training courses from the database.";
    }
}
