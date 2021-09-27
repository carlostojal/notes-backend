package com.carlostojal.notesbackend.Exceptions;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("DUPLICATE_USERNAME");
    }
}
