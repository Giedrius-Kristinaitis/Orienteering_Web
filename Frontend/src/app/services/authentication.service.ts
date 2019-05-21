import {Injectable} from '@angular/core';
import {User} from '../components/user';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {throwError} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  private static readonly host = 'http://104.196.227.120';

  // private static readonly host = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  /**
   * Formats error codes to messages
   * @param status Request status
   */
  static selectErrorMessage(status: number) {
    let message = status.toString();

    switch (status) {
      case 404 : {
        message += ' Request not found';
        break;
      }
      case 400 : {
        message += ' Bad request';
        break;
      }
      case 409 : {
        message += ' Object already exists';
        break;
      }
      case 401: {
        message += ' Wrong email or password';
        break;
      }
      default: {
        message += ' Error not found';
      }
    }

    return message;
  }

  login(email: string, password: string) {
    return this.http.post<User>(`${AuthenticationService.host}/user/login`, {
      email,
      password
    }, httpOptions).pipe(
      catchError(this.handleError),
      map(user => {
      if (user) {
        localStorage.setItem('currentUser', JSON.stringify(user));
      }
    }));
  }

  /**
   * Handles requests errors
   * @param error Error
   */
  private handleError(error) {

    // window.alert(errorMessage);
    return throwError(AuthenticationService.selectErrorMessage(error.status));
  }
}
