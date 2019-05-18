import {Injectable} from '@angular/core';
import {User} from '../components/user';
import {Router} from '@angular/router';
import {EventResponse} from '../components/eventResponse';
import {catchError, retry} from 'rxjs/operators';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {throwError} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // private static readonly host = 'http://104.196.227.120';
  private static readonly host = 'http://localhost:8080';

  constructor(private router: Router,
              private http: HttpClient) {
  }

  getUser(id: number): User {
    return undefined;
  }

  /**
   * Returns user object from local storage
   */
  getCurrentUser(): User {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  /**
   * Removes current user from local storage
   */
  logout(): void {
    localStorage.removeItem('currentUser');
    this.router.navigate(['login']);
  }

  /**
   * Registers user
   * @param user User object
   */
  addUser(user: User) {
    return this.http.post<User>(`${UserService.host}/user/sign-up`, user, httpOptions).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Handles requests errors
   * @param error Error
   */
  private handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error: ${error.status}`;
    }

    // window.alert(errorMessage);
    return throwError(this.selectErrorMessage(errorMessage));
  }

  /**
   * Formats error codes to messages
   * @param status Request status
   */
  selectErrorMessage(status: string): string {
    console.log('Status: ' + status);
    let message = status;

    switch (status) {
      case '404' : {
        message += ' Event not found';
        break;
      }
      case '400' : {
        message += ' Bad request';
        break;
      }
      case '409' : {
        message += ' Event already exists';
        break;
      }
    }

    return message;
  }
}
