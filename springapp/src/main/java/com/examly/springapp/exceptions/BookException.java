package com.examly.springapp.exceptions;

/**
 * The class `BookException` is a custom RuntimeException in Java used for handling exceptions related to books.
 * 
 * @author Adarsh Kumar
 */
public class BookException extends Exception{

    public BookException() {
        super();
    }

    public BookException(String message) {
        super(message);
    }

}
