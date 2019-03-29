import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../../services/event.service";
import {Event} from "../event";
import {async} from "rxjs/internal/scheduler/async";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {
  event: Event;

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService
  ) { }

  ngOnInit() {
    const eventId = +this.route.snapshot.paramMap.get('id');
    this.getEvent(eventId);
  }

  getEvent(id: number): void {
    this.eventService.getEvent(id).subscribe(event => {
      this.event = event;
      console.log(`Event name: ${this.event.name}`);
    });
  }

}
