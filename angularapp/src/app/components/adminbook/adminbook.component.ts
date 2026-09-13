import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-adminbook',
  templateUrl: './adminbook.component.html',
  styleUrls: ['./adminbook.component.css']
})
export class AdminbookComponent {

  bookForm: FormGroup;
  validationMessages: string = '';
  successMessage: string = '';
  formSubmitted = false;
  isEditing: boolean = false;
  bookId: number;
  imageBase64: string;

  book: Book = {
    bookId: 0,
    title: '',
    author: '',
    genre: '',
    description: '',
    rentalFee: 0,
    isAvailable: false,
    coverImage: ''
  }

  constructor(private readonly formBuilder: FormBuilder, private readonly bookService: BookService, private readonly router: Router, private readonly route: ActivatedRoute) { }

  ngOnInit(): void {
    this.bookForm = this.formBuilder.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      genre: ['', Validators.required],
      description: ['', Validators.required],
      rentalFee: [null, [Validators.required, Validators.min(0)]],
      coverImage: ['', Validators.required],
      isAvailable: true
    });

    this.route.queryParams.subscribe(params => {
      this.isEditing = params['isEditing'] === 'true';
      this.bookId = +params['bookId'];
      if (this.bookId && this.isEditing) {
        this.bookService.getBookById(this.bookId).subscribe((book: any) => {
          this.bookForm.patchValue(book);
        });
      } else {
        this.isEditing = false;
      }
    });
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageBase64 = reader.result as string;
        this.bookForm.patchValue({
          coverImage: this.imageBase64
        });
      };
    }
  }


  onSubmit(): void {
    if (this.bookForm.valid) {
      this.formSubmitted = true;
      if (this.isEditing) {
        this.bookService.updateBook(this.bookId, this.bookForm.value).subscribe(
          response => {
            this.successMessage = 'Book updated successfully!';
            this.bookForm.reset();
          },
          error => {
            console.error("Error while updating book", error);
          });
      } else {
        const bookData = {
          ...this.bookForm.value,
          coverImage: this.imageBase64
        };
        this.bookService.addBook(this.bookForm.value).subscribe(
          response => {
            this.successMessage = 'Book added successfully!';
            this.bookForm.reset();
          },
          error => {
            console.error("Error while adding book", error);
          });
      }
    } else {
      Object.keys(this.bookForm.controls).forEach(field => {
        const control = this.bookForm.get(field);
        control?.markAsTouched({ onlySelf: true });
      });
    }
  }


  /**
  * The function `onConfirmSuccess` sets a success message and navigates to the '/adminviewbook' route.
  */
  onConfirmSuccess(): void {
    this.successMessage = ''; // Clear success message after navigation
    this.router.navigate(['/adminviewbook']);
  }

  onBack(): void {
    this.router.navigate(['/adminviewbook']);
  }
}