package com.examly.springapp.exceptions;

/**
 * The `DuplicateBookException` class is a custom RuntimeException used to indicate duplicate book entries.
 * 
 * @author Adarsh Kumar
 */
public class DuplicateBookException extends Exception{

    DuplicateBookException(){
        super();
    }

    public DuplicateBookException(String message) {
        super(message);
    }
}
