package com.examly.springapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 * Represents a Book entity with various attributes such as title, author,
 * genre, description, rental fee, availability, and cover image.
 * 
 * @author Adarsh Kumar
 */

@Entity
public class Book {

    /**
     * 
     * @Id The unique identifier for the book entity (primary key).
     * @GeneratedValue(strategy = GenerationType.IDENTITY) Specifies the generation
     *                          strategy for the primary key.
     * @param bookId      The unique identifier of the book.
     * @param title       The title of the book.
     * @param author      The author of the book.
     * @param genre       The genre of the book.
     * @param description A brief description of the book.
     * @param rentalFee   The fee for renting the book.
     * @param isAvailable Indicates if the book is available for rent.
     * @param coverImage  The image representing the cover of the book.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Specifies the primary key generation strategy for an entity. The primary key
     * is generated using an identity column.
     * 
     * The @Column annotation in the Java code snippet provided is used to define
     * the properties of a column in a database table
     * when mapping a Java entity class to a database table using JPA (Java
     * Persistence API).
     */
    private Long bookId;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, length = 50)
    private String author;
    @Column(nullable = true, length = 50)
    private String genre = "unknown";
    @Column(nullable = true, unique = false, length = 1500)
    private String description;
    @Column(nullable = false, precision = 2)
    private Double rentalFee = 0.0;
    @Column(nullable = false, unique = false)
    private Boolean isAvailable = true;

    // Lob annotation specifies that the database should store the property as Large
    // Object.
    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = true)
    private String coverImage;

    public Book() {
    }

    public Book(Long bookId, String title, String author, String genre, String description, String coverImage) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.coverImage = coverImage;
    }

    public Book(Double rentalFee, Boolean isAvailable) {
        this.rentalFee = rentalFee;
        this.isAvailable = isAvailable;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(Double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}