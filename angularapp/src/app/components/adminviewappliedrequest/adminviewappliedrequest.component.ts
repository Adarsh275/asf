import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookRentalRequest } from 'src/app/models/book-rental-request.model';
import { BookrentalrequestService } from 'src/app/services/bookrentalrequest.service';

@Component({
  selector: 'app-adminviewappliedrequest',
  templateUrl: './adminviewappliedrequest.component.html',
  styleUrls: ['./adminviewappliedrequest.component.css']
})
export class AdminviewappliedrequestComponent implements OnInit {

  bookRentalRequests: BookRentalRequest[];
  bookRentalRequestById: BookRentalRequest;
  rentalId: number;
  statusFilter: string = "All";
  searchTitle: string;
  displayModal = false;
  selectedRequest = null;

  constructor(private readonly bookRentalRequestService: BookrentalrequestService,
      private readonly activatedRoute: ActivatedRoute,
      private readonly router: Router
    ) { }
    
    ngOnInit(): void {
    this.rentalId = +this.activatedRoute.snapshot.paramMap.get('rentalId');
    this.getAllBookRentalRequests();
  }

  getAllBookRentalRequests() {
    this.bookRentalRequestService.getAllBookRentalRequest().subscribe((data) => {
      this.bookRentalRequests = data;
    })
  }

  getBookRentalRequestsById() {
    this.bookRentalRequestService.getBookRentalRequestById(this.rentalId).subscribe((data) => {
      this.bookRentalRequestById = data;
    })
  }

  approveRequest(id: number,request: BookRentalRequest): void {
    request.status = 'Approved';
    this.bookRentalRequestService.updateBookRentalRequest(id, request).subscribe(() => {
      this.getAllBookRentalRequests();
    })
  }

  rejectRequest(id: number,request: BookRentalRequest): void {
    request.status = 'Rejected';
    this.bookRentalRequestService.updateBookRentalRequest(id, request).subscribe(() => {
      this.getAllBookRentalRequests();
    })
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
