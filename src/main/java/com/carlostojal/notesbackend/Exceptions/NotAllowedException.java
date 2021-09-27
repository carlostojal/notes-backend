package com.carlostojal.notesbackend.Exceptions;

public class NotAllowedException extends RuntimeException {

    public NotAllowedException() {
        super("NOT_ALLOWED");
    }
}
