package de.ait.usermanagment.exceptions;

public class UserIsNotExistsException extends RuntimeException {
    public UserIsNotExistsException(String message) {
        super(message);
    }
}

