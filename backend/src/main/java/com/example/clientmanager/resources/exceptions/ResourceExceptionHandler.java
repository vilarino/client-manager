package com.example.clientmanager.resources.exceptions;

import com.example.clientmanager.services.exceptions.DataBaseException;
import com.example.clientmanager.services.exceptions.ResouceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFount(ResouceNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();
        error.setMsg(e.getMessage());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource not found");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();
        error.setMsg(e.getMessage());
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database exception");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status.value()).body(error);
    }
}
