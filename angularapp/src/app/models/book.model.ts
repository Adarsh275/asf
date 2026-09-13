/**
 * Represents a book in the library system.
 * bookId?: number; // An optional property that represents the unique identifier of the book.
 * 
 * @author Adarsh Kumar
 */

export interface Book {
    bookId?: number;
    title?: string;
    author?: string;
    genre?: string;
    description?: string;
    rentalFee?: number;
    isAvailable?: boolean;
    coverImage?: string;
}