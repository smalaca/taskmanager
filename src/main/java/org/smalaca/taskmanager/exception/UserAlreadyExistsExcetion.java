package org.smalaca.taskmanager.exception;

public class UserAlreadyExistsExcetion extends RuntimeException {
    public UserAlreadyExistsExcetion(String message) {
        super(message);
    }
}
