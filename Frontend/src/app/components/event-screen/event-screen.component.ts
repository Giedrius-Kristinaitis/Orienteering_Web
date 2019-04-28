import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-event-screen',
  templateUrl: './event-screen.component.html',
  styleUrls: ['./event-screen.component.css']
})
export class EventScreenComponent implements OnInit {
  eventName = '';

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  getEventName(name: string): void {
    this.eventName = name;
  }
}
