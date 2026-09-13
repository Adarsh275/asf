import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { Login } from 'src/app/models/login.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  loginForm: FormGroup;
  submitted = false;
  isLoading = false;

  constructor(private readonly formBuilder: FormBuilder,private readonly authService:AuthService,private readonly router:Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  login(): void {
    this.isLoading = true;
    this.submitted = true;
    if (this.loginForm.valid) {
      const loginData: Login = this.loginForm.value; // Get form value
      console.log('Login data:', loginData); // Log the login data for debugging
      this.authService.login(loginData).subscribe(
        response => {
          console.log('Login successful:', response);
          alert('Login successful!');
          
          this.authService.initializeAuthState();
          const decoder: any = jwtDecode(response);
          localStorage.setItem('token', response);
          localStorage.setItem('role', decoder.role);
          localStorage.setItem('userId', decoder.user_id);
          localStorage.setItem('username', decoder.username);
          this.isLoading = false;
          this.router.navigate(['/home']); 
        },
        error => {
          console.error('Login failed:', error);
          this.isLoading = false;
          alert('Login failed! Please check your credentials.');
        }
      );
    }
  }
 
}

 
 


