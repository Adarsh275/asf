import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
 
  registrationform: FormGroup
  registrationSuccess: boolean = false
 
  constructor(private readonly authService: AuthService, private readonly fb: FormBuilder, private readonly router: Router) {
    this.registrationform = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8),Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$')]],
      username: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      userRole: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    }, { validator: this.passwordMatchValidator })
  }
 
  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password').value;
    const confirmPassword = formGroup.get('confirmPassword').value;
    return password === confirmPassword ? null : { match: true };
  }
 
  onSubmit() {
    if (this.registrationform.valid) {
      this.authService.register(this.registrationform.value).subscribe(
        (result) => {
          this.registrationSuccess = true
          this.registrationform.reset();
          alert("Registration successful");
          this.router.navigate(['/login']);
        },
        (error) => {
          alert("Registration not done");
        }
      )
    }
  }
  redirectToLogin() {
    this.router.navigate(['/login'])
  }
}