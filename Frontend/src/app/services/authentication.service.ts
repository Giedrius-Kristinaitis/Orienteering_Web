import { Injectable } from '@angular/core';
import {User} from "../components/user";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, Subject, Subscription} from "rxjs";
import {map} from "rxjs/operators";

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

  login(email: string, password: string) {
    return this.http.post<User>('http://localhost:8080/user/login', {email: email, password: password}, httpOptions).pipe(map(user => {
      if(user) {
        localStorage.setItem('currentUser', JSON.stringify(user));
      }
    }));
  }
}
