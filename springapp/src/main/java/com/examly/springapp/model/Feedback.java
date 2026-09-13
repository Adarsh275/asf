package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
/**
 * Entity class representing a Feedback.
 */
public class Feedback {
    /**
     * Unique identifier for a feedback.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long feedbackId;
    @NotBlank(message = "Please provide a feedback")
    private String feedbackText;
    private LocalDate date;

    /**
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `Book` entity.
     * Uses `@JoinColumn` to specify the foreign key column name in the database.
     */
    @ManyToOne
    @JoinColumn(name="userId",nullable=false)
    private User user;

    public Feedback() {
    }

    public Feedback(Long feedbackId, String feedbackText, LocalDate date, User user) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    



    

}
