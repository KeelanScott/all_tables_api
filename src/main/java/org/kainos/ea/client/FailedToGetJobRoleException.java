package org.kainos.ea.client;

public class FailedToGetJobRoleException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to get job role from the database";
    }

}
