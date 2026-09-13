import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookRentalRequest } from 'src/app/models/book-rental-request.model';
import { BookrentalrequestService } from 'src/app/services/bookrentalrequest.service';

@Component({
  selector: 'app-userviewappliedrequest',
  templateUrl: './userviewappliedrequest.component.html',
  styleUrls: ['./userviewappliedrequest.component.css']
})
export class UserviewappliedrequestComponent implements OnInit {

  bookRentals: BookRentalRequest[]=[];
  userId: number;
  searchTitle: string;
  displayModal: boolean = false;
  displayConfirmModal: boolean = false;
  selectedRequest = null;
  deleteRequestId: number | null = null;

  constructor(private readonly bookRentalRequestService: BookrentalrequestService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly router: Router) {
    }
    
    ngOnInit(): void {
      this.userId=+localStorage.getItem('userId')     
      this.getBookRentalRequestsByUserId();
    }
    
    getBookRentalRequestsByUserId() {
      this.bookRentalRequestService.getBookRentalRequestByUserId(this.userId).subscribe((data) => {
        this.bookRentals = data;
    })
  }

  deleteRequest(rentalId: number) {
    this.bookRentalRequestService.deleteBookRentalRequest(rentalId).subscribe(() => {
      this.getBookRentalRequestsByUserId();
    })
  }

  openConfirmModal(rentalId: number) {
    this.deleteRequestId = rentalId;
    this.displayConfirmModal = true;
  }

  closeConfirmModal() {
    this.displayConfirmModal = false;
    this.deleteRequestId = null;
  }

  confirmDelete() {
    if (this.deleteRequestId !== null) {
      this.deleteRequest(this.deleteRequestId);
      this.closeConfirmModal();
    }
  }

  toggleModal(request) {
    this.selectedRequest = request;
    this.displayModal = true;
  }

  closeModal() {
      this.displayModal = false;
      this.selectedRequest = null;
  }
}
