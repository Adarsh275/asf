import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
/**
 * @author Sushmitha
 * @class UserAddFeedbackComponent
 * Component class for adding user feedback.
 */
export class UseraddfeedbackComponent implements OnInit {
  
  feedbackForm: FormGroup;
  submitted = false;
  showPopup: boolean = false;
  currentUser: number;
  feedbackResponse: Feedback;

  constructor(private readonly fb: FormBuilder, private readonly feedbackService: FeedbackService, private router: Router) {
    this.feedbackForm = this.fb.group({
      feedbackText: ['', Validators.required]
    });
  }

  ngOnInit(): void {
      this.currentUser = +localStorage.getItem('userId');
    }

  onSubmit() {
    this.submitted = true;
    if (this.feedbackForm.valid) {
      const feedback: Feedback = {
        feedbackText: this.feedbackForm.value.feedbackText,
        user: { userId: this.currentUser },
        date: new Date()
      };
      this.feedbackService.sendFeedback(feedback).subscribe((feedbackResponse: Feedback) => {
        console.log("Feedback successfully sent:", feedbackResponse);
        this.feedbackResponse = feedbackResponse;
        this.showPopup = true;
      }, error => {
        console.error("Error sending feedback:", error);
      });
    }
  }

  closePopup() {
    this.submitted = false;
    this.feedbackForm.reset();
    this.showPopup = false;
    this.router.navigate(['/userviewfeedback']);
  }
}
