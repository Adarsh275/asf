package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Feedback;

/**
 * @author Sushmitha
 * Repository interface for managing `Feedback` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
 */

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long>{

    List<Feedback> findByUserUserId(Long userId);
}
