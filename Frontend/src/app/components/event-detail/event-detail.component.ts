import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../../services/event.service";
import {Event} from "../event";

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
    this.getEvent();
    console.log(`Event name: ${this.event.name}`);
  }

  getEvent(): void {
    const eventId = +this.route.snapshot.paramMap.get('id');
    this.eventService.getEvent(eventId).subscribe(event => this.event = event);
  }

}
