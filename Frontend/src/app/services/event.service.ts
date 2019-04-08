import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import { Event } from "../components/event";
import {HttpClient} from "@angular/common/http";
import {EventResponse} from "../components/eventResponse";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  /**
   * Returns all mocked events
   */
  getEvents(): Observable<EventResponse> {
    return this.http.get<EventResponse>('http://localhost:8080/api/event/page/0/10');
  }

  getEvent(id: number): Observable<Event> {
    //return of(EVENTS.find(event => event.id === id));
    return this.http.get<Event>(`http://localhost:8080/api/event/${id}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
