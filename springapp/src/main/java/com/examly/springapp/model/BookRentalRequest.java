package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

/* 
* @Author Vardhan
 * Entity class representing a BookRentalRequest
 */
@Entity
public class BookRentalRequest {

    /*
     * Unique identifier for a BookRentalRequest
     * Annotated with '@Id' to denote the primary key
     * Uses '@GeneratedValue' with the strategy 'GenerationType.AUTO' for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    /*
     * User associated with the BookRentalRequest
     * 
     * Annotated with '@ManyToOne' to denote the many-to-one relationship with the 'User' entity.
     * Uses '@JoinColumn' to specify the foreign key column name in the database.
     */
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    /*
     * Book associated with the BookRentalRequest
     * 
     * Annotated with '@ManyToOne' to denote the many-to-one relationship with the 'Book' entity.
     * Uses '@JoinColumn' to specify the foreign key column name in the database.
     */
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;


    private LocalDate requestDate;
    private LocalDate returnDate;
    private String status = "Pending";
    private String comments;

       
    public BookRentalRequest() {
    }

    public BookRentalRequest(Long rentalId, User user, Book book, LocalDate requestDate, LocalDate returnDate,
    String status, String comments) {
        this.rentalId = rentalId;
        this.user = user;
        this.book = book;
        this.requestDate = requestDate;
        this.returnDate = returnDate;
        this.status = status;
        this.comments = comments;
    }


    @PrePersist
    /*
     * Method executed before the entity is persisted
     * Sets the request date to the current date
     * Sets the return date based on the current date plus the rent days
     */
    public void onCreate() {
        this.requestDate = LocalDate.now();
    }
    
    /*
     * Method executed before the entity is updated
     * Updates the return date based on the current date plus the rent days if rent days is not null
     */
    
    public Long getRentalId() {
        return rentalId;
    }
    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public LocalDate getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }  
}