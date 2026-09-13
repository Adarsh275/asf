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
import com.examly.springapp.model.BookRentalRequest;
import com.examly.springapp.service.BookRentalRequestService;

/**
 * @author Vardhan
 * Controller class for managing organizer-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/` base path using `@RequestMapping`.
 */
                                                                                                
@RestController
@RequestMapping("/api")
public class BookRentalRequestController {

    /**
     * Service implementation for BookRentalRequest operations.
     * Annotated with `@Autowired` to inject the `BookRentalRequestService` bean.
     */

    private BookRentalRequestService bookRentalRequestService;

    public BookRentalRequestController(BookRentalRequestService bookRentalRequestService) {
        this.bookRentalRequestService = bookRentalRequestService;
    }

    /**
     * Retrieves all list of book rental requests.
     *
     * @return a `ResponseEntity` containing a list of `BookRentalRequest` entities.
     */    
    @GetMapping("/bookrentalrequest")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<List<BookRentalRequest>> getAllBookRentalRequests() {
        List<BookRentalRequest> bookRentalRequestResponse = bookRentalRequestService.getAllBookRentalRequests();
        if(bookRentalRequestResponse == null) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(200).body(bookRentalRequestResponse);
    }

    /**
     * Retrieves a bookrentalrequest by User ID.
     * @param userID the ID of the bookrentalrequest to be retrieved.
     * @return a `ResponseEntity` containing the `BookRentalRequest` entity or an error status.
     */
    @GetMapping("/bookrentalrequest/user/{userId}")
    @PreAuthorize(value = "hasAnyRole('ADMIN') or hasAnyRole('USER')")
    public ResponseEntity<List<BookRentalRequest>> getBookRentalRequestsByUserId(@PathVariable Long userId) {
        List<BookRentalRequest> bookRentalRequestResponse = bookRentalRequestService.getBookRentalRequestsByUserId(userId);
        if(bookRentalRequestResponse == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(bookRentalRequestResponse);
    }

    /**
     * Retrieves a bookrentalrequest by ID.
     * @param id the ID of the bookrentalrequest to be retrieved.
     * @return a `ResponseEntity` containing the `BookRentalRequest` entity or an error status.
     */
    @GetMapping("/bookrentalrequest/{id}")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<Optional<BookRentalRequest>> getBookRentalRequestById(@PathVariable Long id) {
        Optional<BookRentalRequest> bookRentalRequestResponse = bookRentalRequestService.getBookRentalRequestById(id);
        if(bookRentalRequestResponse.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(bookRentalRequestResponse);
    }

    /**
     * Adds a new bookrentalrequest.
     * @param bookrentalrequest the `BookRentalRequest` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `BookRentalRequest` entity or an error status.
     */
    @PostMapping("/bookrentalrequest")
    @PreAuthorize(value = "hasAnyRole('USER')")
    public ResponseEntity<BookRentalRequest> addBookRentalRequest(@RequestBody BookRentalRequest bookRentalRequest) throws BookAlreadyRentedException{
        BookRentalRequest bookRentalRequestResponse = bookRentalRequestService.addBookRentalRequest(bookRentalRequest);
        if(bookRentalRequestResponse == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(201).body(bookRentalRequestResponse);
    }
    
    
    /**
     * Updates an existing bookrentalrequest by ID.
     * @param id the ID of the bookrentalrequest to be updated.
     * @param bookRentalRequest  the updated `BookRentalRequest` entity provided in the request body.
     * @return a `ResponseEntity` containing the updated `BookRentalRequest` entity or an error status.
     */
    
    //this method is not allowed to user role but can be included with restricted access.
    @PutMapping("/bookrentalrequest/{id}")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<BookRentalRequest> updateBookRentalRequest(@PathVariable Long id, @RequestBody BookRentalRequest bookRentalRequest) {
        BookRentalRequest bookRentalRequestResponse = bookRentalRequestService.updateBookRentalRequest(id, bookRentalRequest);
        if(bookRentalRequestResponse == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(bookRentalRequestResponse);
    }
    
    /**
     * Deletes a bookrentalrequest by ID.
     *
     * @param id the ID of the bookrentalrequest to be deleted.
     * @return a `ResponseEntity` indicating the success or failure of the operation.
     */
    @DeleteMapping("/bookrentalrequest/{id}")
    @PreAuthorize(value = "hasAnyRole('USER')")
    public ResponseEntity<BookRentalRequest> deleteBookRentalRequest(@PathVariable Long id) {
        BookRentalRequest bookRentalRequestResponse = bookRentalRequestService.deleteBookRentalRequest(id);
        if(bookRentalRequestResponse == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(bookRentalRequestResponse);
    }   

}
