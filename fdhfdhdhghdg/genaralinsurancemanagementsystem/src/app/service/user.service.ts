import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, of } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string="http://localhost:3000/user";

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  getUserProfile(): Observable<User | null>{
    return of (this.authService.getUserProfileFromStorage());
  }

  updateUserProfile(user:User): Observable<User | null>{
   localStorage.setItem('userprofile', JSON.stringify(User));
    return this.http.put<User>(`${this.baseUrl}/${user.id}`,user);
  }
}
