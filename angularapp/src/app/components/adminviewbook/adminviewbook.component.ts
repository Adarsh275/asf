import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-adminviewbook',
  templateUrl: './adminviewbook.component.html',
  styleUrls: ['./adminviewbook.component.css']
})
export class AdminviewbookComponent implements OnInit {

  books: Book[] = [];
  genres: string[] = [];

  searchByTitle: string = '';
  filterByGenre: string = '';

  bookStatus: string = "Available";
  showImageModel: boolean = false;
  selectedImage: string = '';


  constructor(private readonly bookService: BookService, private readonly router: Router) { }

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks() {
    this.bookService.getAllBooks().subscribe(date => {
      this.books = date;
      this.genres = [...new Set(this.books.map(book => book.genre))];   // Extract unique genres from the books

    });
  }
  
  deleteBook(book: Book) {
    this.bookService.deleteBook(book.bookId).subscribe(() => {
      alert('Book deleted successfully!');
      this.getBooks();
    });
  }

  confirmDelete(book: Book) {
    if (confirm('Are you sure you want to delete this book?')) {
      this.deleteBook(book);
    }
  }

  editBook(book: Book): void {
    this.router.navigate(['/adminbook'],{queryParams:{isEditing:true, bookId:book.bookId}});
  }

  toggleAvailability(book: Book) {
    book.isAvailable = !book.isAvailable;
    this.bookService.updateBook(book.bookId, book).subscribe(() => {
      alert('Book availability updated successfully!');
    });
  }

  showCoverImage(imageUrl: string) {
    this.selectedImage = imageUrl;
    this.showImageModel = true;
  }

  closeImageModel() {
    this.showImageModel = false;
    this.router.navigate(['/adminviewbook']);
  }

}