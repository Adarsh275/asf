import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-userviewbooks',
  templateUrl: './userviewbooks.component.html',
  styleUrls: ['./userviewbooks.component.css']
})
export class UserviewbooksComponent implements OnInit {

  books: Book[] = [];
  searchBooks: string = '';
  selectedImage: string = '';
  showImageModel: boolean = false;
  userId: number;

  constructor(private readonly bookService: BookService, private readonly router: Router) { }

  ngOnInit(): void {
    this.getAllBooks();
    this.userId = +localStorage.getItem('userId')
  }


  getAllBooks() {
    this.bookService.getAllBooks().subscribe((data: Book[]) => {
      this.books = data;
    });
  }

  showCoverImage(imageUrl: string) {
    this.showImageModel = true;
    this.selectedImage = imageUrl;
  }

  closeImageModel() {
    this.showImageModel = false;
  }
    
  rentBook(bookId: number) {
    this.router.navigate(['/useraddrequest', bookId]);
  }

}
