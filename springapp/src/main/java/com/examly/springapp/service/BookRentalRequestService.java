package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.exceptions.BookAlreadyRentedException;
import com.examly.springapp.model.BookRentalRequest;

/**
 * @author Vardhan
 * Service interface for managing book rental request operations.
 * 
 * Defines the contract for the `BookRentalRequest` class, providing methods for book rental request CRUD operations.
 */
public interface BookRentalRequestService {

    /**
     * Retrieves all book rental requests.
     *
     * @return a list of all `BookRentalRequest` entities.
     */
    List<BookRentalRequest> getAllBookRentalRequests();

    /**
     * Retrieves book rental requests by the user ID.
     *
     * @param userId the ID of the user whose book rental requests are to be retrieved.
     * @return a list of `BookRentalRequest` entities associated with the specified user ID.
     */
    List<BookRentalRequest> getBookRentalRequestsByUserId(Long userId);

    /**
     * Retrieves a book rental request by its ID.
     *
     * @param requestId the ID of the book rental request to be retrieved.
     * @return an `Optional` containing the `BookRentalRequest` entity if found, or empty if not found.
     */
    Optional<BookRentalRequest> getBookRentalRequestById(Long requestId);

    /**
     * Adds a new book rental request to the database.
     *
     * @param request the `BookRentalRequest` entity to be added.
     * @return the saved `BookRentalRequest` entity.
     * @throws BookAlreadyRentedException if a duplicate book rental request is detected.
     */
    BookRentalRequest addBookRentalRequest(BookRentalRequest request) throws BookAlreadyRentedException;

    /**
     * Updates an existing book rental request.
     *
     * @param requestId the ID of the book rental request to be updated.
     * @param request the updated `BookRentalRequest` entity.
     * @return the updated `BookRentalRequest` entity.
     */
    BookRentalRequest updateBookRentalRequest(Long requestId, BookRentalRequest request);

    /**
     * Deletes a book rental request by its ID.
     *
     * @param requestId the ID of the book rental request to be deleted.
     * @return the deleted `BookRentalRequest` entity.
     */
    BookRentalRequest deleteBookRentalRequest(Long requestId);
}
