import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent {


  username = this.authService.username$;
  role: string = "USER";

  isBooksDropdownOpen = false;
  isFeedbackDropdownOpen = false;

  constructor(private authService: AuthService, private router: Router) { }

  logout() {
    this.authService.logout().subscribe(()=>{
      this.router.navigate(['/home'])
    })
    
  }

  toggleBooksDropdown() {
    this.isBooksDropdownOpen = !this.isBooksDropdownOpen;
  }

  toggleFeedbackDropdown() {
    this.isFeedbackDropdownOpen = !this.isFeedbackDropdownOpen;
  }


  isloggedout(){
    return this.authService.logout()
  }
}
