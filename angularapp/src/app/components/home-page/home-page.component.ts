import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {

  constructor(private authService:AuthService) { }

  isLoggedIn(){
    return this.authService.isLoggedIn().valueOf()
  }

  isloggedout(){
    return this.authService.logout().valueOf()
  }
  
  isAdmin(){
    return this.authService.isAdmin().valueOf()
  }
  
  isUser(){
    return this.authService.isUser().valueOf()
  }

}
