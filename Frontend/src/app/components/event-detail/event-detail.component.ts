import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../../services/event.service";
import {Event} from "../event";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {
  @Output() public eventName: EventEmitter<string> = new EventEmitter();
  event: Event;

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService
  ) {
  }

  ngOnInit() {
    const eventId = +this.route.snapshot.paramMap.get('id');
    this.getEvent(eventId);
  }

  getEvent(id: number): void {
    this.eventService.getEvent(id).subscribe(event => {
      this.event = event;
      this.eventName.emit(event.name);
      // console.log(this.event.estimatedDistanceMetres);
      // console.log(`Event name: ${this.event.name}`);
    });
  }

}
