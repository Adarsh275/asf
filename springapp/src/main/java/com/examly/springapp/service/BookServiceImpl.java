package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.examly.springapp.exceptions.BookDeletionException;
import com.examly.springapp.exceptions.BookException;
import com.examly.springapp.exceptions.DuplicateBookException;
import com.examly.springapp.model.Book;
import com.examly.springapp.repository.BookRepo;

/**
 * Implementation of the BookService interface that interacts with a BookRepo.
 * @param bookRepo The repository for managing books
 * 
 * @author Adarsh Kumar
 */

@Service
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;
    private static final String ERROR_MESSAGE = "Book with ID : %d does not exist";

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    /**
     * The function `addBook` in Java checks if a book with the same title already exists in the repository and throws an exception if it does, otherwise it saves the book to the repository.
     * @param book The `book` parameter in the `addBook` method represents an instance of the `Book` class that is being added to a book repository.
     * @return The `addBook` method is returning a `Book` object.
    */

    @Override
    public Book addBook(Book book) throws DuplicateBookException, BadRequest {
        if (bookRepo.existsByTitle(book.getTitle())) {
            throw new DuplicateBookException(String.format("Book with title %s already exists", book.getTitle()));
        }
        return bookRepo.save(book);

    }

    /**
    * This function retrieves a book by its ID from a repository and returns it as an Optional.
    * @param bookId The `bookId` parameter is of type `Long` and represents the unique identifier of a book.
    * @return An Optional object containing a Book entity is being returned.
    */
    @Override
    public Optional<Book> getBookById(Long bookId) throws BookException{
        if(!bookRepo.existsById(bookId)){
            throw new BookException(String.format(ERROR_MESSAGE, bookId));
        }
        return bookRepo.findById(bookId);
    }

    /**
    * The function getAllBooks() overrides a method to return a list of all books from a book repository.
    * @return A list of all books from the book repository is being returned.
    */
    @Override
    public List<Book> getAllBooks() throws BookException{
        if(bookRepo.findAll().isEmpty()){
            throw new BookException("No book is present in the Library");
        }
        return bookRepo.findAll();
    }

    /**
     * The `updateBook` function updates the details of a book with the provided bookId using the information from the updatedBook object.
     * @param bookId The `bookId` parameter in the `updateBook` method represents the unique identifier of
     * the book that you want to update.
     * @param updatedBook The `updatedBook` parameter in the `updateBook` method represents the new
     * information that will be used to update an existing book in the system.
     * @return The `updateBook` method is returning the updated `Book` object after saving it in the database.
     */
    @Override
     public Book updateBook(Long bookId, Book updatedBook) throws BookException{
        Optional<Book> oldBook = getBookById(bookId);
        if (!oldBook.isPresent()) {
            throw new BookException(String.format(ERROR_MESSAGE, bookId));
        }
        Book book = oldBook.get();

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setGenre(updatedBook.getGenre());
        book.setDescription(updatedBook.getDescription());
        book.setRentalFee(updatedBook.getRentalFee());
        book.setIsAvailable(updatedBook.getIsAvailable());
        book.setCoverImage(updatedBook.getCoverImage());
        
        return bookRepo.save(book);
    }

    /**
     * The function deletes a book by its ID after checking if it exists and is not referenced in rental requests.
     * @param bookId The `deleteBook` method takes a `Long` parameter `bookId` which represents the unique identifier of the book that needs to be deleted from the system.
     * @return The `deleteBook` method is returning the `Book` object that was deleted from the database.
     */
    @Override
    public Book deleteBook(Long bookId) throws BookDeletionException, BookException {
        Optional<Book> bookToDelete = getBookById(bookId);
        if (!bookToDelete.isPresent()) {
            throw new BookException(String.format(ERROR_MESSAGE, bookId));
        } else {
            Book book = bookToDelete.get();
            boolean check = book.getIsAvailable();
            if (!check) {
                throw new BookDeletionException(String.format("Book with ID : %d is rented by a user", bookId));
            }
            bookRepo.delete(book);
            return book;
        }
    }
    
}
