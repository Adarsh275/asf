package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exceptions.BookAlreadyRentedException;
import com.examly.springapp.exceptions.BookDeletionException;
import com.examly.springapp.exceptions.BookException;
import com.examly.springapp.exceptions.DuplicateBookException;
import com.examly.springapp.model.Book;
import com.examly.springapp.service.BookService;


/**
 * BookController is a REST controller that handles HTTP requests for managing books.
 * It provides endpoints to add, view, update, and delete books.
 * This controller uses BookService to perform the actual operations.
 * 
 * @author Adarsh Kumar
 * 
 */

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    /**
    * Endpoints:
    * - POST /api/books: Adds a new book.
    * - GET /api/books/{bookId}: Retrieves a book by its ID.
    * - GET /api/books: Retrieves all books.
    * - PUT /api/books/{bookId}: Updates an existing book by its ID.
    * - DELETE /api/books/{bookId}: Deletes a book by its ID.
    */
    
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Adds a new book to the collection.
     * @param book the book to be added
     * @return a ResponseEntity containing the added book and a status code of 201 if successful, or a status code of 400 if the book could not be added
     * @throws DuplicateBookException if the book already exists in the collection
     */
    @PostMapping
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody Book book) throws DuplicateBookException {
        Book newBook = bookService.addBook(book);
        return ResponseEntity.status(201).body(newBook);
    }
    
    /**
     * Retrieves a book by its ID.
     * @param bookId the ID of the book to retrieve
     * @return a ResponseEntity containing the book if found, or a 404 status if not found
     * @throws BookException 
    */
        @GetMapping("/{bookId}")
        @PreAuthorize(value = "hasAnyRole('ADMIN')")
        public ResponseEntity<Book> getBookById(@PathVariable Long bookId) throws BookException{
        Optional<Book> requestedBook = bookService.getBookById(bookId);
        if (!requestedBook.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(requestedBook.get());
    }

    /**
     * Retrieves a list of all books.
     * @return ResponseEntity containing the list of books. If no books are found,
     *         returns a ResponseEntity with status 400 and an empty list.
    * @throws BookException 
    */
        @GetMapping
        @PreAuthorize(value = "hasAnyRole('USER') or hasAnyRole('ADMIN')" )
        public ResponseEntity<List<Book>> getAllBooks() throws BookException {
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.status(200).body(bookList);
    }

    /**
     * Updates an existing book with the provided details.
     * @param bookId the ID of the book to be updated
     * @param book the book object containing the updated details
     * @return a ResponseEntity containing the updated book if successful, or a 404 status if the book is not found
     */
    @PutMapping("/{bookId}")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book book) throws BookException, BookAlreadyRentedException{
        Book updatedBook = bookService.updateBook(bookId, book);
        return ResponseEntity.status(200).body(updatedBook);
    }
    
    /**
     * Deletes a book by its ID.
     * @param bookId the ID of the book to be deleted
     * @return ResponseEntity containing the deleted book if found, or a 404 status if the book was not found
     * @throws BookException 
     * @throws BookDeletionException 
     */
    @DeleteMapping("/{bookId}")
    // @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<Book> deleteBook(@PathVariable Long bookId) throws BookDeletionException, BookException {
        Book deletedBook = bookService.deleteBook(bookId);
        return ResponseEntity.ok().body(deletedBook);
    }
}
