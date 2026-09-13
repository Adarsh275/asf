package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.examly.springapp.exceptions.BookDeletionException;
import com.examly.springapp.exceptions.BookException;
import com.examly.springapp.exceptions.DuplicateBookException;
import com.examly.springapp.model.Book;

/*
 * This code snippet is defining a Java interface named `BookService`. 
 * This interface declares several methods that define the contract for a service that manages books.
 * 
 * @author Adarsh Kumar
*/

public interface BookService {

    Book addBook(Book book) throws DuplicateBookException, BadRequest;

    Optional<Book> getBookById(Long bookId) throws BookException;
    
    List<Book> getAllBooks() throws BookException;

    Book updateBook(Long bookId, Book updatedBook) throws BookException;

    Book deleteBook(Long bookId) throws BookDeletionException, BookException;
}
