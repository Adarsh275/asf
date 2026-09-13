package com.examly.springapp.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.BookAlreadyRentedException;
import com.examly.springapp.model.Book;
import com.examly.springapp.model.BookRentalRequest;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.BookRentalRequestRepo;
import com.examly.springapp.repository.BookRepo;
import com.examly.springapp.repository.UserRepo;

/**
 * Service implementation for managing book rental request operations.
 * @author Vardhan
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the `BookRentalRequestService` interface to provide specific business logic.
 */

@Service
public class BookRentalRequestServiceImpl implements BookRentalRequestService{


    // Repository for handling book rental request data operations
    private BookRentalRequestRepo bookRentalRequestRepo;
    private UserRepo userRepo;
    private BookRepo bookRepo;
    /**
     * Constructor for BookRentalRequestServiceImpl.
     * 
     * @param bookRentalRequestRepo The repository to handle book rental requests.
     */
    public BookRentalRequestServiceImpl(BookRentalRequestRepo bookRentalRequestRepo,UserRepo userRepo,BookRepo bookRepo) {
        this.bookRentalRequestRepo = bookRentalRequestRepo;
        this.userRepo = userRepo;
        this.bookRepo=bookRepo;
    }

    /**
     * Adds a new bookrentalrequest to the database.
     *
     * @param request the `BookRentalRequest` entity to be added.
     * @return the saved `BookRentalRequest` entity.
     */
    @Override
    public BookRentalRequest addBookRentalRequest(BookRentalRequest request) throws BookAlreadyRentedException{
        boolean bookIsAvailable = request.getBook().getIsAvailable();
        if(!Boolean.TRUE.equals(bookIsAvailable)) {
            throw new BookAlreadyRentedException("BookRentalRequest already exists!");
        }
        User id = request.getUser();
        Long userId = id.getUserId();
        User user = userRepo.findById(userId).orElse(null);
        Book bid = request.getBook();
        Long bookId = bid.getBookId();
        Book book = bookRepo.findById(bookId).orElse(null);
        if(user!=null){
            request.setUser(user);
        }
        if(book!=null){
            book.setIsAvailable(false);
            request.setBook(book);
        }
        bookRentalRequestRepo.save(request);
        return request;
    }
 

    /**
     * Retrieves all bookrentalrequest from the database.
     *
     * @return a list of all `BookRentalRequest` entities.
     */
    @Override
    public List<BookRentalRequest> getAllBookRentalRequests() {
        return bookRentalRequestRepo.findAll();
    }

    /**
     * Retrieves a list of book rental requests belonging to a specific userId.
     *
     * @param userID the ID of the user.
     * @return a list of book rental requests associated with the user.
     */
    @Override
    public List<BookRentalRequest> getBookRentalRequestsByUserId(Long userId) {
        if(userId == null) return Collections.emptyList();
        return bookRentalRequestRepo.findByUserUserId(userId);
    }


    /**
     * Retrieves a book rental request by ID.
     *
     * @param id the ID of the book rental request to retrieve.
     * @return the `BookRentalRequest` entity if found
     */
    @Override
    public Optional<BookRentalRequest> getBookRentalRequestById(Long requestId) {
        return bookRentalRequestRepo.findById(requestId);
    }

    /**
     * Updates an existing book rental request by ID.
     *
     * @param requestId the ID of the book rental request to update.
     * @param request the updated `BookRentalRequest` entity details.
     * @return the updated `BookRentalRequest` entity if found, or `null` otherwise.
     */
    @Override
    public BookRentalRequest updateBookRentalRequest(Long requestId, BookRentalRequest request) {
        BookRentalRequest bookRentalRequest = getBookRentalRequestById(requestId).orElse(null);
        if(bookRentalRequest!=null){
            bookRentalRequest.setRequestDate(request.getRequestDate());
            bookRentalRequest.setReturnDate(request.getReturnDate());
            bookRentalRequest.setStatus(request.getStatus());
            return bookRentalRequestRepo.save(bookRentalRequest);
        }
        return null;
    }
    
    /**
     * Deletes a book rental request by ID.
     *
     * @param id the ID of the book rental request to delete.
     * @return the deleted `BookRentalRequest` entity if found, or `null` otherwise.
     */
    @Override
    public BookRentalRequest deleteBookRentalRequest(Long requestId) {
        BookRentalRequest bookRentalRequest = getBookRentalRequestById(requestId).orElse(null);
        if(bookRentalRequest == null) return null;
        bookRentalRequestRepo.deleteById(requestId);
        return bookRentalRequest;

    }

    /**
     * Extends the rental period for a book rental request.
     *
     * @param requestId the ID of the book rental request to extend.
     * @param extensionDays the number of days to extend the rental period.
     * @return the updated `BookRentalRequest` entity if found, or `null` otherwise.
     */
    public BookRentalRequest extendRentalPeriod(Long requestId, int extensionDays) {
        BookRentalRequest bookRentalRequest = getBookRentalRequestById(requestId).orElse(null);
        if (bookRentalRequest != null) {
            bookRentalRequest.setReturnDate(bookRentalRequest.getReturnDate().plusDays(extensionDays));
            return bookRentalRequestRepo.save(bookRentalRequest);
        }
        return null;
    }

}