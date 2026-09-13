import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { map } from 'rxjs/operators';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { APP_URL } from '../app.constant';
 
 
@Injectable({
  providedIn: 'root'
})
export class AuthService {
 
  private readonly apiUrl=APP_URL;
 
  public  userRoleSubject = new BehaviorSubject<string | null>(null);
  private  usernameSubject = new BehaviorSubject<string | null>(null);
  private  userIdSubject = new BehaviorSubject<number | null>(null);

  constructor(private readonly http:HttpClient, private route:Router) { 
    this.initializeAuthState();
  }

  register(user:User):Observable<any>{
    return this.http.post(this.apiUrl+"/register",user)
  }
 
  login(login: Login): Observable<any> {
    console.log("AuthService: ", login);
    return this.http.post<string>(`${this.apiUrl}/login`, login, { responseType: 'text' as 'json' })
      .pipe(
        map(token => {
          localStorage.setItem('authToken', token);
          this.setAuthToken(token);
          this.initializeAuthState();
          return token;
        })
      );
  }
 
  public initializeAuthState(): void {
    const token = this.getAuthToken();
    // console.log(token);
   
    if (token && typeof token === 'string') {
      try {
        const decoded: any = jwtDecode(token);
        if (decoded?.userId) {
          this.userIdSubject.next(decoded.userId);
          this.userRoleSubject.next(decoded.userRole);
          this.usernameSubject.next(decoded.username);
          // console.log(decoded.userId);
          // console.log(decoded.username);
        }
      } catch (error) {
        console.error('Invalid token specified: must be a valid JWT', error);
        this.logout();
      }
    } else {
      console.error('Invalid token specified: must be a string');
      this.logout();
    }
  }

  logout(): Observable<void>{
    localStorage.clear()
    this.userRoleSubject.next(null);
    this.route.navigate(['/login']);
    this.decodeToken(null);
    this.isLoggedIn();
    this.isAdmin();
    this.isUser();
    return ;
   
  }
 
  private decodeToken(token: string): any {
   if(token){
    const decoded: any = jwtDecode(token);
  
    if (decoded) {
      this.userRoleSubject.next(decoded.role);
      this.usernameSubject.next(decoded.sub);
      if (decoded.userId) {
        this.userIdSubject.next(decoded.userId);
      }
      return decoded;
    }
   }
   return null;
  }
 
  private setAuthToken(token: string): void {
    localStorage.setItem('authToken', token);
    this.decodeToken(token);
  }
 
  getAuthToken(): string | null {
    return localStorage.getItem('authToken');
  }
 
  getAuthHeaders(): HttpHeaders {
    const token = this.getAuthToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
 
  get userRole$(): Observable<string | null> {
    return this.userRoleSubject.asObservable();
  }
 
  get username$(): Observable<string | null> {
    return this.usernameSubject.asObservable();
  }
 
  get userId$(): Observable<number | null> {
    return this.userIdSubject.asObservable();
  }
 
  isLoggedIn(): boolean {
     if(this.getAuthToken()){
      return true;
     }else{
      console.log('checking is logged in');
      
      return false;
     };
  }
 
  isAdmin(): boolean {
    let token  = localStorage.getItem('authToken')  ?? null;
    this.decodeToken(token);
    return this.userRoleSubject.value === 'ROLE_ADMIN';
  }
 
  isUser(): boolean {
    return this.userRoleSubject.value === 'ROLE_USER';
  }
 
  getUserDetails(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user?email=${email}`, {
      headers: this.getAuthHeaders()
    });
  } 
}