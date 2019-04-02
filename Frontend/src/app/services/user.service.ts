import { Injectable } from '@angular/core';
import {User} from "../components/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getUser(id: number): User {
    return undefined;
  }

  getCurrentUser(): User {
    return JSON.parse(localStorage.getItem('currentUser'));
  }
}
