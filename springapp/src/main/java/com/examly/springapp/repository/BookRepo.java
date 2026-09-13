package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Book;

/*
 * This code snippet is defining a repository interface in a Java Spring application extends the `JpaRepository` interface provided by Spring Data JPA.. 
 * The `@Repository` annotation is used to indicate that the interface is a Spring Data repository.
 * 
 * @author Adarsh Kumar
 */

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    /**
     * The function `existsByTitle` checks if an item with a specific title exists.
     * @param title The `existsByTitle` method takes a `String` parameter named `title`, which represents the title of an item.
     * @return The method `existsByTitle` returns a boolean value.
     */
    boolean existsByTitle(String title);
}