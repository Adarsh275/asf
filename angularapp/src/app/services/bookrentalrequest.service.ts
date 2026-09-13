import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookRentalRequest } from '../models/book-rental-request.model';
import { AuthService } from './auth.service';
import { APP_URL } from '../app.constant';

@Injectable({
  providedIn: 'root'
})
export class BookrentalrequestService {

  public readonly backendUrl: string = APP_URL+"/bookrentalrequest";

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getAuthToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
  constructor(private readonly http: HttpClient, private authService: AuthService) { }

  addBookRentalRequest(request: BookRentalRequest): Observable<BookRentalRequest> {
    return this.http.post<BookRentalRequest>(this.backendUrl, request, { headers: this.getAuthHeaders() });
  }

  getAllBookRentalRequest(): Observable<BookRentalRequest[]> {
    return this.http.get<BookRentalRequest[]>(this.backendUrl,{ headers: this.getAuthHeaders() });
  }

  getBookRentalRequestByUserId(userId: number): Observable<BookRentalRequest[]> {
    return this.http.get<BookRentalRequest[]>(`${this.backendUrl}/user/${userId}`,{ headers: this.getAuthHeaders() });
  }

  getBookRentalRequestById(rentalId: number): Observable<BookRentalRequest> {
    return this.http.get<BookRentalRequest>(`${this.backendUrl}/${rentalId}`,{ headers: this.getAuthHeaders() });
  }

  updateBookRentalRequest(rentalId: number, request: BookRentalRequest): Observable<BookRentalRequest> {
    return this.http.put<BookRentalRequest>(`${this.backendUrl}/${rentalId}`,request,{ headers: this.getAuthHeaders() });
  }

  deleteBookRentalRequest(rentalId:number): Observable<void>{
    return this.http.delete<void>(`${this.backendUrl}/${rentalId}`,{ headers: this.getAuthHeaders() });
  }
}
