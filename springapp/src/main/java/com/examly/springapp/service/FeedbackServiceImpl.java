package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

/**
 * @author Sushmitha
 *         Service implementation for managing organizer-related operations.
 * 
 *         Annotated with `@Service` to indicate it's a Spring service class.
 *         Implements the `Feedback Service` interface to provide specific
 *         business logic.
 */

@Service

public class FeedbackServiceImpl implements FeedbackService {

    /**
     * Repository for managing `Feedback` entity data access.
     * Annotated with `@Autowired` to inject the `Feedback` bean.
     */

    private FeedbackRepo feedbackRepo;
    private UserRepo userRepo;
    private static final  String ERROR_MESSAGE = "Feedback with Id %d not found.";

    public FeedbackServiceImpl(FeedbackRepo feedbackRepo, UserRepo userRepo) {
        this.feedbackRepo = feedbackRepo;
        this.userRepo= userRepo;
    }

    /**
     * Creates a new feedback entry.
     * Checks if the feedback object is null and saves it to the repository.
     * 
     * @param feedback the feedback object to be created
     * @return the saved feedback object, or null if the input is null
     */

     @Override
     public Feedback createFeedback(Feedback feedback) throws EntityNotFoundException {
         User user = feedback.getUser();
         Long userId = user.getUserId();
         User newUser = userRepo.findById(userId).orElse(null);
        if(newUser!=null){
            feedback.setUser(user);
            feedbackRepo.save(feedback);
            return feedback;
        }
        throw new EntityNotFoundException("user with id "+userId+" is not found.");
     }

    /**
     * Retrieves a feedback by ID.
     *
     * @param feedbackId the ID of the feedback to retrieve.
     * @return the `Feedback` entity if found, or `null` otherwise.
     */

    @Override
    public Feedback getFeedbackById(Long feedbackId) throws EntityNotFoundException {
        Feedback feedbackss = feedbackRepo.findById(feedbackId).orElse(null);
        if (feedbackss == null) {
            throw new EntityNotFoundException(String.format(ERROR_MESSAGE,feedbackId));
        }
        return feedbackss;

    }

    /**
     * Retrieves all feedbacks from the database.
     *
     * @return a list of all `Feedback` entities.
     */

    @Override
    public List<Feedback> getAllFeedbacks() throws BadRequest {
        return feedbackRepo.findAll();
    }

    /**
     * Deletes a feedback by ID.
     *
     * @param id the ID of the feedback to delete.
     * @return the deleted `Feedback` entity if found, or `null` otherwise.
     */

    @Override
    public Feedback deleteFeedback(Long feedbackId) throws EntityNotFoundException {
        Feedback feed = feedbackRepo.findById(feedbackId).orElse(null);
        if (feed != null) {
            feedbackRepo.deleteById(feedbackId);
        } else {
            throw new EntityNotFoundException(String.format(ERROR_MESSAGE,feedbackId));
        }
        return feed;
    }

    /**
     * Retrieves a feedback by UserId.
     *
     * @param userId the ID of the feedback to retrieve.
     * @return the `Feedback` entity if found, or `null` otherwise.
     */

    @Override
    public List<Feedback> getFeedbacksByUserId(Long userId) throws EntityNotFoundException {
        List<Feedback> feedback = feedbackRepo.findByUserUserId(userId);
        if (feedback.isEmpty()) {
            throw new EntityNotFoundException(String.format(ERROR_MESSAGE,userId));
        }
        return feedback;
    }
}
