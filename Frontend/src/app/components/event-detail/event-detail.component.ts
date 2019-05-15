import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../../services/event.service';
import {Event} from '../event';
import {MatDialog} from '@angular/material';
import {EventJoinComponent} from '../event-join/event-join.component';

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
    private eventService: EventService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {
    const eventId = this.route.snapshot.paramMap.get('id');
    this.getEvent(eventId);
  }

  getEvent(id: string): void {
    this.eventService.getEvent(id).subscribe(event => {
      this.event = event;
      this.eventName.emit(event.name);
      // console.log(this.event.estimatedDistanceMetres);
      // console.log(`Event name: ${this.event.name}`);
    });
  }

  /**
   * Opens event join dialog
   * @param marker Marker
   */
  openDialog(event: Event): void {
    const dialogRef = this.dialog.open(EventJoinComponent, {
      data: event
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      // if (result === true) {
      //   this.deleteMarker(marker);
      // } else if (typeof result === 'object') {
      //   marker.name = result.name;
      // }
    });
  }

}
