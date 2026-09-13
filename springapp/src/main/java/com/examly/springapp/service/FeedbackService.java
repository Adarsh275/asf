package com.examly.springapp.service;

import java.util.List;

import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.examly.springapp.model.Feedback;

import jakarta.persistence.EntityNotFoundException;

/**
 * @author Sushmitha
 * Service interface for handling feedback-related operations.
 * Provides methods for creating, retrieving, deleting feedback, and 
 * retrieving feedback by user ID.
 */
public interface FeedbackService {

    /**
     * Create a new feedback entry.
     * 
     * @param feedback the feedback to be created
     * @return the created feedback
    */
    Feedback createFeedback(Feedback feedback) throws EntityNotFoundException;

    /**
     * Retrieve feedback by its ID.
     * 
     * @param feedbackId the ID of the feedback
     * @return the feedback with the specified ID
    */
    Feedback getFeedbackById(Long feedbackId)throws EntityNotFoundException;

    /**
     * Retrieve all feedback entries.
     * 
     * @return a list of all feedback
    */
    List<Feedback> getAllFeedbacks()throws BadRequest;

    /**
     * Delete a feedback entry by its ID.
     * 
     * @param feedbackId the ID of the feedback to be deleted
     * @return the deleted feedback
    */
    Feedback deleteFeedback(Long feedbackId)throws EntityNotFoundException;

    /**
     * Retrieve feedback entries by user ID.
     * 
     * @param userId the ID of the user
     * @return a list of feedback from the specified user
    */
    List<Feedback> getFeedbacksByUserId(Long userId)throws EntityNotFoundException;
}
