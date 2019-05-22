import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../../services/event.service';
import {Event} from '../event';
import {MatDialog} from '@angular/material';
import {EventJoinComponent} from '../event-join/event-join.component';
import {EventTeamProgressDialogComponent} from '../event-team-progress-dialog/event-team-progress-dialog.component';
import {UserService} from '../../services/user.service';
import {MessageService} from '../../services/message.service';
import {interval} from 'rxjs';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css']
})
export class EventDetailComponent implements OnInit {
  static readonly updateInterval = 5000;
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

    interval(EventDetailComponent.updateInterval).subscribe(
      () => {
        this.eventService.getEvent(eventId).subscribe(event => {
          this.event.status = event.status;
          this.event.photos = event.photos;
          this.event.teamSize = event.teamSize;
          this.event.description = event.description;
          this.event.estimatedDistanceMetres = event.estimatedDistanceMetres;
          this.event.estimatedTimeMillis = event.estimatedTimeMillis;
          this.event.starting = event.starting;
          this.event.name = event.name;

          if (this.event.checkpointCount !== event.checkpointCount) {
            this.event.checkpoints = event.checkpoints;
          }

          this.eventName.emit(this.event.name);
        });
      }
    );
  }

  /**
   * Loads event data
   * @param id Event id
   */
  getEvent(id: string): void {
    this.eventService.getEvent(id).subscribe(event => {
      this.event = event;
      this.eventName.emit(event.name);
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
    this.changeEventStatus('In progress');
  }

  isUserJoined(): boolean {
    const currentUserId = this.userService.getCurrentUser().id;
    let isJoined = false;

    this.event.teams.forEach(team => {
      if (team.members.find(user => user.id === currentUserId)) {
        isJoined = true;
      }
    });

    return isJoined;
  }

  stopEvent(): void {
    this.changeEventStatus('Closed');
  }

  changeEventStatus(status: string): void {
    let tempEvent;
    this.eventService.getEvent(this.event.id).subscribe(
      data => {
        tempEvent = data;
      },
      error => {
        this.messageService.add(error);
      },
      () => {
        tempEvent.status = status;
        // console.log(tempEvent);
        this.eventService.updateEvent(tempEvent).subscribe(
          data => {
          },
          error => {
            this.messageService.add(error);
          },
          () => {
            this.event = tempEvent;
          }
        );
      }
    );
  }

}
