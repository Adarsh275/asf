import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'angularapp';
  isLoggedIn: boolean = false;
  // isUser: boolean = false;

  constructor(private authService: AuthService,private route:Router) { }

  ngOnInit(): void {
    this.authService.userRole$.subscribe(role => {
      this.isLoggedIn = this.authService.isLoggedIn();            
    });
  }
  
  isloggedout(){
    return this.authService.logout()
  }
  
  isAdmin(){    
    return this.authService.isAdmin().valueOf()
  }
  
  isUser(){
    // console.log("is user in");
    return this.authService.isUser().valueOf()
  }
}
