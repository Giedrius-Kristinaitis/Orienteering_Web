import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-event-detail-screen',
  templateUrl: './event-detail-screen.component.html',
  styleUrls: ['./event-detail-screen.component.css']
})
export class EventDetailScreenComponent implements OnInit {
  eventName: string;

  constructor() { }

  ngOnInit() {
  }

  getEventName(name: string) : void {
    this.eventName = name;
    // console.log(`Name: ${name}`);
  }
}
