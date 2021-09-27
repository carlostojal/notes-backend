package com.carlostojal.notesbackend.RestControllers;

import com.carlostojal.notesbackend.Exceptions.NotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotAllowedAdvice {

    @ResponseBody
    @ExceptionHandler(NotAllowedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String notAllowedHandler(NotAllowedException ex) {
        return ex.getMessage();
    }
}
