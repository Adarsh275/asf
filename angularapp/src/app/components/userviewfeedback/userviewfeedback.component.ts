import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
 
/**
 * @author Sushmitha
 * @class AdminViewComponent
 * Component class for displaying feedbacks and showing user profiles.
 */
export class UserviewfeedbackComponent implements OnInit {
  feedbackList: Feedback[]=[];
  showPopup = false;
  feedbackToDelete: number | null = null;
 
  userId:number
 
  constructor(private readonly feedbackService:FeedbackService,private readonly route:ActivatedRoute) { }
 
  ngOnInit(): void {
 
    this.userId=+localStorage.getItem('userId')
    this.getAllFeedbacks()
  }
 
 
  getAllFeedbacks(){
    this.feedbackService.getAllFeedbacksByUserId(this.userId).subscribe((data)=>{
      this.feedbackList=data
    })
  }
  confirmDelete(index: number): void {
    this.feedbackToDelete = index;
    this.showPopup = true;
  }
 
  deleteFeedback(): void {
    this.feedbackService.deleteFeedback(this.feedbackToDelete).subscribe(()=>{
        this.feedbackToDelete = null;
          this.showPopup = false;
          this.getAllFeedbacks()
    })
  }
 
  cancelDelete(): void {
    this.feedbackToDelete = null;
    this.showPopup = false;
  }
}