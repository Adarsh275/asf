import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookRentalRequest } from 'src/app/models/book-rental-request.model';
import { BookrentalrequestService } from 'src/app/services/bookrentalrequest.service';
 
@Component({
  selector: 'app-useraddrequest',
  templateUrl: './useraddrequest.component.html',
  styleUrls: ['./useraddrequest.component.css']
})
 
export class UseraddrequestComponent implements OnInit {
 
  requestForm: FormGroup;
  currentUser: number;
  currentBook: number;
  today: string;
 
 
  constructor(private readonly bookRentalRequestService: BookrentalrequestService,
    private readonly router: Router,
    private readonly activatedRoute: ActivatedRoute,
    private readonly formBuilder: FormBuilder
    ) { }
 
  ngOnInit(): void {
    this.today = new Date().toISOString().split('T')[0];
    this.requestForm = this.formBuilder.group({
      returnDate: ['', [Validators.required, this.futureDateValidator]],
      comments: ['', Validators.required]
    })
    this.currentUser = +localStorage.getItem('userId');
    this.currentBook = +this.activatedRoute.snapshot.paramMap.get('bookId');    
  }
 
  futureDateValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const currentDate = new Date();
    const selectedDate = new Date(control.value);
    currentDate.setHours(0, 0, 0, 0);
    selectedDate.setHours(0, 0, 0, 0);
    if (selectedDate < currentDate ) {
      return { 'pastDate': true };
    }
    return null;
  }
 
  addBookRentalRequest() {
    if(!this.requestForm.valid) return;
    const rentalRental: BookRentalRequest = {
      comments: this.requestForm.value.comments,
      user: {
        userId: this.currentUser
      },
      book: {
        bookId: this.currentBook
      },
      returnDate: this.requestForm.value.returnDate
    };
    this.bookRentalRequestService.addBookRentalRequest(rentalRental).subscribe(() => {
      this.showModal();
    });
  }
 
  showModal() {
    document.getElementById("successModal").style.display = "block";
    setTimeout(() => {
      this.closeModal();
    }, 2000);
  }
 
  closeModal() {
    document.getElementById("successModal").style.display = "none";
    this.router.navigate(['/userviewbooks']);
  }
 
  backButton() {
    this.router.navigate(['/userviewbooks']);
  }
 
}