package com.examly.springapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

import jakarta.persistence.EntityNotFoundException;

/**
 * @author Sushmitha
 * Controller class for testing basic endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api` base path using `@RequestMapping`.
 */

@RestController
@RequestMapping("/api")
public class FeedbackController {

    /**
     * Service implementation for feedback operations.
     * Annotated with `@Autowired` to inject the `feedbackService` bean.
     */

    private FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Adds a new feedback.
     * @param feedback the `Feedback` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `Feedback` entity or an error status.
     */
    
     @PostMapping("/feedback")
     public ResponseEntity<Feedback> addFeedBack(@RequestBody Feedback feedBack) {
         return ResponseEntity.status(201).body(feedbackService.createFeedback(feedBack));
     }   
    
    /**
     * Retrieves a feedback by ID.
     * @param feedbackId the ID of the feedback to be retrieved.
     * @return a `ResponseEntity` containing the `Feedback` entity or an error status.
    */
    
    @GetMapping("/feedback/{id}")
    @PreAuthorize(value = "hasAnyRole('ADMIN') or hasAnyRole('USER')")
    public ResponseEntity<Feedback> viewFeedbackbyId(@PathVariable Long id)throws EntityNotFoundException{
        Feedback feed = feedbackService.getFeedbackById(id);
        return ResponseEntity.status(200).body(feed);
     
    }

    
    /**
     * Retrieves all feedbacks.
     * @return a `ResponseEntity` containing a list of all `Feedback` entities.
     */
    @GetMapping("/feedback")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<List<Feedback>> viewAllFeedbacks()throws BadRequest{
        List<Feedback> feedb1 = feedbackService.getAllFeedbacks();
        return ResponseEntity.status(200).body(feedb1);
        
    }
    
    /**
     * Retrieves a feedback by userId.
     * @param userId the ID of the user to be retrieved.
     * @return a `ResponseEntity` containing the `Feedback` entity or an error status.
     */
    
    @GetMapping("/feedback/user/{userId}")
    @PreAuthorize(value = "hasAnyRole('ADMIN') or hasAnyRole('USER')")
    public ResponseEntity<List<Feedback>> viewFeedbacksbyUser(@PathVariable Long userId)throws EntityNotFoundException{
        List<Feedback> feedList = feedbackService.getFeedbacksByUserId(userId);
        return ResponseEntity.status(200).body(feedList);
    }
    /**
     * Deletes a feedback by ID.
     *
     * @param feedbackId the ID of the feedback to be deleted.
     * @return a `ResponseEntity` indicating the success or failure of the operation.
     */
    @DeleteMapping("/feedback/{feedbackId}")
    @PreAuthorize(value = "hasAnyRole('USER')")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable Long feedbackId)throws EntityNotFoundException{
        Feedback feedbacks2 = feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.status(200).body(feedbacks2);
        
    }

}
