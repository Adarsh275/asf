import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../models/book.model';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { APP_URL } from '../app.constant';

/**
 * The `BookService` class provides methods to interact with a book API including fetching, adding, updating, and deleting books.
 * 
 * @author Adarsh Kumar
 */
@Injectable({
  providedIn: 'root'
})

export class BookService {

  public readonly apiUrl = APP_URL+"/books";

  constructor(private readonly http: HttpClient, private readonly authService:AuthService) { }

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getAuthToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  /**
   * The function `getAllBooks` makes an HTTP GET request to the specified API URL and returns an Observable of type `Book[]`.
   * @returns An Observable of type Book array is being returned.
   */
  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl,{ headers: this.getAuthHeaders() });
  }


  /**
   * This function retrieves a book by its ID from an API using HTTP GET request.
   * @param {number} bookld - The `bookId` parameter in the `getBookById` function is a number that
   * represents the unique identifier of the book you want to retrieve from the API. 
   * This function uses the `http.get` method to make a GET request to the API endpoint with the specified `bookId` to fetch.
   * @returns An Observable of type Book is being returned.
   */
  getBookById(bookld: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${bookld}`,{ headers: this.getAuthHeaders() });
  }

  /**
   * The addBook function sends a POST request to the specified API URL with the book data and returns
   * an Observable of the Book type.
   * @param {Book} book - The `addBook` method is used to add a new book to a collection. The `book`
   * parameter represents the book object that you want to add to the collection. It typically contains
   * information such as the title, author, genre, publication date, etc.
   * @returns An Observable of type Book is being returned.
   */
  addBook(book: Book): Observable<Book> {
    console.log("in service");
    return this.http.post<Book>(this.apiUrl, book,{ headers: this.getAuthHeaders() });
  }


  /**
   * The function `updateBook` sends a PUT request to update a book with the specified ID using the
   * provided book data.
   * @param {number} bookld - The `bookId` parameter is the unique identifier of the book that you want
   * to update. It is a number that helps identify the specific book in the database.
   * @param {Book} book - The `book` parameter in the `updateBook` method represents the updated book
   * object that you want to save or update in the database. It should be of type `Book`, which likely
   * contains properties such as `title`, `author`, `isbn`, etc. This object will be sent in the
   * @returns The `updateBook` method is returning an Observable of type `Book`.
   */
  updateBook(bookld: number, book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/${bookld}`, book,{ headers: this.getAuthHeaders() });
  }


  /**
   * The `deleteBook` function sends a DELETE request to the API to delete a book with the specified ID.
   * @param {number} bookld - The `bookId` parameter is the unique identifier of the book that you want
   * to delete from the server. This identifier is used to locate the specific book resource that needs
   * to be deleted.
   * @returns An Observable of void is being returned.
   */
  deleteBook(bookld: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${bookld}`,{ headers: this.getAuthHeaders() });
  }
}
