import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const userRole = this.authService.userRoleSubject.value;

    if (!this.authService.isLoggedIn() || !this.authService.isAdmin() && !this.authService.isUser()) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
