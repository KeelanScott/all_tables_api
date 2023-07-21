package org.kainos.ea.exceptions;

public class FailedToGetTrainingCoursesException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to get training courses from the database.";
    }
}
