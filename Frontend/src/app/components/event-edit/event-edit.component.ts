import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../../services/event.service';
import {Event} from '../event';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css']
})
export class EventEditComponent implements OnInit {
  event: Observable<Event>;

  constructor(private route: ActivatedRoute,
              private eventService: EventService) {
  }

  ngOnInit() {
    this.event = new Observable<Event>();
    const eventId = this.route.snapshot.paramMap.get('id');
    this.getEvent(eventId);
  }

  getEvent(id: string): void {
    this.event = this.eventService.getEvent(id);
  }
}
