import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {Event} from '../components/event';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EventResponse} from '../components/eventResponse';
import {catchError, retry} from 'rxjs/operators';
import {User} from '../components/user';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class EventService {

  // http://104.196.227.120
  // private static readonly host = 'http://104.196.227.120';
  private static readonly host = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  /**
   * Returns all mocked events
   */
  getEvents(page, size): Observable<EventResponse> {
    return this.http.get<EventResponse>(`${EventService.host}/api/event/page/${page}/${size}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Returns event with given id
   * @param id Event id
   */
  getEvent(id: string): Observable<Event> {
    // return of(EVENTS.find(event => event.id === id));
    return this.http.get<Event>(`${EventService.host}/api/event/${id}`).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Adds a new event to event list
   * @param event New event
   */
  addEvent(event, ownerId) {
    return this.http.post<Event>(`${EventService.host}/api/event/${ownerId}`, event, httpOptions).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Updates event
   * @param event Updated event
   */
  updateEvent(event: Event) {
    const tempEvent = new Event(event);
    tempEvent.estimatedTimeMillis = 3600000 * tempEvent.estimatedTimeMillis;
    console.log(tempEvent);
    return this.http.put<Event>(`${EventService.host}/api/event/${tempEvent.id}`, JSON.stringify(tempEvent), httpOptions).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  /**
   * Deletes event form list
   * @param id Event id
   */
  deleteEvent(id) {
    return this.http.delete<Event>(`${EventService.host}/api/event/${id}`, httpOptions).pipe(
      retry(1),
      catchError(this.handleError),
    );
  }

  addTeamMember(eventId: string, teamId: string, user: User) {
    console.log('Service: ' + user);
    return this.http.post<User>(`${EventService.host}/api/event/team/member/${eventId}/${teamId}`, user, httpOptions).pipe(
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
  selectErrorMessage(status: string) {
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
