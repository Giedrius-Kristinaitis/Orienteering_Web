import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../../services/event.service';
import {Event} from '../event';
import {MatDialog} from '@angular/material';
import {EventJoinComponent} from '../event-join/event-join.component';
import {EventTeamProgressDialogComponent} from '../event-team-progress-dialog/event-team-progress-dialog.component';
import {UserService} from '../../services/user.service';
import {MessageService} from '../../services/message.service';

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
    private dialog: MatDialog,
    private userService: UserService,
    private messageService: MessageService
  ) {
  }

  ngOnInit() {
    this.messageService.clear();
    const eventId = this.route.snapshot.paramMap.get('id');
    this.getEvent(eventId);
  }

  /**
   * Loads event data
   * @param id Event id
   */
  getEvent(id: string): void {
    this.eventService.getEvent(id).subscribe(event => {
      // console.log(event);
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
  openJoinDialog(event: Event): void {
    const dialogRef = this.dialog.open(EventJoinComponent, {
      data: event
    });

    dialogRef.afterClosed().subscribe();
  }

  openProgressDialog(event: Event): void {
    const dialogRef = this.dialog.open(EventTeamProgressDialogComponent, {
      data: event
    });

    dialogRef.afterClosed().subscribe();
  }

  startEvent(): void {
    this.event.status = 'In progress';
    this.eventService.updateEvent(this.event).subscribe(
      data => {},
      error => {
        this.messageService.add(error);
      },
      () => {}
    );
  }

}
