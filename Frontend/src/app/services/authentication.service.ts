import { Injectable } from '@angular/core';
import {User} from "../components/user";
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  constructor(private http: HttpClient) { }

  login(email: string, password: string): boolean {
    this.http.post<User>('http://localhost:8080/user/login', {email: email, password: password}, httpOptions).subscribe(user => {
      if(user) {
        localStorage.setItem('currentUser', JSON.stringify(user));

        return true;
      }
    });
    return false;
  }
}
