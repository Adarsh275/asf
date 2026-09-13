package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.BookRentalRequest;

/**
 * @author Vardhan
 * Repository interface for `BookRentalRequest` entity.
 * 
 * Extends JpaRepository to provide CRUD operations on the `BookRentalRequest` entity.
 * Additionally, defines a custom method to retrieve book rental requests by user ID.
 */
@Repository
public interface BookRentalRequestRepo extends JpaRepository<BookRentalRequest, Long> {

    /**
     * Retrieves book rental requests by the user ID.
     *
     * @param userId the ID of the user whose book rental requests are to be retrieved.
     * @return a list of `BookRentalRequest` entities associated with the specified user ID.
     */
    List<BookRentalRequest> findByUserUserId(Long userId);

}
