package com.examly.springapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import jakarta.persistence.EntityNotFoundException;

/**
 * The `GlobalExceptionHandler` class in Java handles various exceptions related to book operations and entity not found scenarios.
 * 
 * @author Adarsh Kumar
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BookDeletionException.class)
    public ResponseEntity<String> bookDeletionException(BookDeletionException exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }
     
    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> bookException(BookException exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @ExceptionHandler(DuplicateBookException.class)
    public ResponseEntity<String> duplicateBookException(DuplicateBookException exception){
        return ResponseEntity.status(409).body(exception.getMessage());
    }

    @ExceptionHandler(BookAlreadyRentedException.class)
    public ResponseEntity<String> bookAlreadyRentedException(BookAlreadyRentedException exception){
        return ResponseEntity.status(409).body(exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<String> badRequestException(BadRequest exception){
        return ResponseEntity.status(400).body(exception.getMessage());
    }
    
}