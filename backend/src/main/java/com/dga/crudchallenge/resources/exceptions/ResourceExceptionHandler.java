package com.dga.crudchallenge.resources.exceptions;

import com.dga.crudchallenge.services.exceptions.DatabaseException;
import com.dga.crudchallenge.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<com.dga.crudchallenge.resources.exceptions.StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
        com.dga.crudchallenge.resources.exceptions.StandardError err = new com.dga.crudchallenge.resources.exceptions.StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage("Resource not found");
        err.setError(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<com.dga.crudchallenge.resources.exceptions.StandardError> database(DatabaseException e, HttpServletRequest request){
        com.dga.crudchallenge.resources.exceptions.StandardError err = new com.dga.crudchallenge.resources.exceptions.StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Database exception");
        err.setError(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
