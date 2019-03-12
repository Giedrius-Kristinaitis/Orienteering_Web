import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import { Event } from "../components/event";
import { EVENTS} from "../components/mock-events";

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor() { }

  getEvents(): Observable<Event[]> {
    return of(EVENTS);
  }
}