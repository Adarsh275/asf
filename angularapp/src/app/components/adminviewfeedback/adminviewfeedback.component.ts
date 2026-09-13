import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
/**
 * @author Sushmitha
 * @class AdminViewComponent
 * Component class for displaying feedbacks and showing user profiles.
 */
export class AdminviewfeedbackComponent implements OnInit {
  feedbacks:Feedback[]=[];
  selectedFeedback: Feedback | null = null;
  userDetails:any
  user: User;
 
  constructor(private readonly feedbackService:FeedbackService,private readonly authService:AuthService) { }
 
  ngOnInit(): void {
    this.feedbackService.getFeedbacks().subscribe((data)=>{
      this.feedbacks=data;
    })

    this.authService.username$.subscribe((email)=>{
      if(email){
        // console.log(email);
        this.getUserDetails(email)
      }
    })

  }

  getUserDetails(email:string){
    this.authService.getUserDetails(email).subscribe((data)=>{
      this.userDetails=data
    })
}
 
  showProfile(feedback: Feedback): void {
    this.user = feedback.user;
  }
 
  closeProfile(): void {
    this.user = null;
  }
}



 