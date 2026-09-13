import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Feedback } from '../models/feedback.model';
import { Observable } from 'rxjs';
import { APP_URL } from '../app.constant';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
 
/**
 * @author Sushmitha
 * @class FeedbackService
 * Service class for handling feedback-related operations.
 */
export class FeedbackService {

  /**
   * Utilizes HttpClient for making HTTP requests.
   * @apiUrl Base URL for the API endpoint.
   */
  public readonly apiUrl:string = APP_URL+"/feedback";

  constructor(private readonly http:HttpClient, private readonly authService:AuthService) { }

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getAuthToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }


  /**
   * Sends feedback to the server.
   * @param {Feedback} feedback - The feedback object to send.
   * @returns {Observable<Feedback>} Observable of the sent feedback.
   */

  sendFeedback(feedback:Feedback):Observable<Feedback>{
    return this.http.post<Feedback>(this.apiUrl,feedback,{ headers: this.getAuthHeaders()});
  }
 
 
  /**
   * Retrieves all feedbacks by a specific user ID.
   * @param {number} userId - The ID of the user.
   * @returns {Observable<Feedback[]>} Observable of an array of feedback objects.
   */

  getAllFeedbacksByUserId(userId:number):Observable<Feedback[]>{
    return this.http.get<Feedback[]>(`${this.apiUrl}/user/${userId}`,{ headers: this.getAuthHeaders()});
  }
 
  /**
   * Deletes a feedback by its ID.
   * @param {number} feedbackId - The ID of the feedback to delete.
   * @returns {Observable<void>} Observable of void.
   */
 
  deleteFeedback(feedbackId:number):Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${feedbackId}`,{ headers: this.getAuthHeaders()});
  }
 
  /**
   * Retrieves all feedbacks from the server.
   * @returns {Observable<Feedback[]>} Observable of an array of feedback objects.
   */
 
  getFeedbacks():Observable<Feedback[]>{
    return this.http.get<Feedback[]>(this.apiUrl,{ headers: this.getAuthHeaders()});
  }
}
 