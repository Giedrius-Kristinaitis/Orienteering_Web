import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {Event} from '../components/event';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EventResponse} from '../components/eventResponse';
import {catchError, retry} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) {
  }

  /**
   * Returns all mocked events
   */
  getEvents(): Observable<EventResponse> {
    return this.http.get<EventResponse>('http://localhost:8080/api/event/page/1/10').pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEvent(id: string): Observable<Event> {
    // return of(EVENTS.find(event => event.id === id));
    return this.http.get<Event>(`http://localhost:8080/api/event/${id}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  addEvent(event) {
    return this.http.post<Event>('http://localhost:8080/api/event/', event, httpOptions).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  private handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    // window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
