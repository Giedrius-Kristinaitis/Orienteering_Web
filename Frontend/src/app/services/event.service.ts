import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import { Event } from "../components/event";
import {HttpClient} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  /**
   * Returns all mocked events
   */
  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>("http://localhost:8080/api/event/page/0/10").pipe(
      tap(_ => console.log(`trying to get events`)),
      catchError(this.handleError('getEvents', [])));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
