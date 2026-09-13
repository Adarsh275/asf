package com.examly.springapp.exceptions;

/**
 * Custom exception to handle scenarios where bookrentalrequest already exists.
 * Extends `Exception` to allow unchecked exceptions.
 * 
 * @author Venkata Vardhan
 */
public class BookAlreadyRentedException extends Exception{

    public BookAlreadyRentedException() {}

    /**
     * Constructor for `DuplicateBookRentalRequest`.
     *
     * @param message the exception message describing the error.
     */
    public BookAlreadyRentedException(String message) {
        super(message);
    }


}
