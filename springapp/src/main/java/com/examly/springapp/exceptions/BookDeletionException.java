package com.examly.springapp.exceptions;

/**
 * The `BookDeletionException` class is a custom RuntimeException used for handling exceptions related to book deletion.
 * 
 * @author Adarsh Kumar
 */
public class BookDeletionException extends Exception{

    public BookDeletionException() {
        super();
    }
    
    public BookDeletionException(String message) {
        super(message);
    }
}
