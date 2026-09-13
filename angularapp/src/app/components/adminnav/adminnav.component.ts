import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent{
  role: string = "ADMIN";
  username:string=''

  constructor(private authService:AuthService) { }

  logout(){
    return this.authService.logout()
  }

}
