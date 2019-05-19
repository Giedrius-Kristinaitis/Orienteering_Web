import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {MarkerEditDialogComponent} from '../marker-edit-dialog/marker-edit-dialog.component';
import {Event} from '../event';
import {EventService} from '../../services/event.service';
import {UserService} from '../../services/user.service';
import {MessageService} from '../../services/message.service';
import {Team} from '../team';
import {Photo} from '../photo';
import {interval} from 'rxjs';

@Component({
  selector: 'app-event-team-progress-dialog',
  templateUrl: './event-team-progress-dialog.component.html',
  styleUrls: ['./event-team-progress-dialog.component.css']
})
export class EventTeamProgressDialogComponent implements OnInit {
  showedPhotos: Photo[];

  constructor(public dialogRef: MatDialogRef<MarkerEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Event,
              private eventService: EventService,
              private userService: UserService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.messageService.clear();

    interval(3000).subscribe(
      () => {
        this.updateEvent();
      }
    );
  }

  /**
   * Closes dialog
   */
  onHideClick() {
    this.dialogRef.close(false);
  }

  /**
   * Gets team's photos from the server
   * @param team Team
   */
  expandedTeam(team: Team) {
    this.eventService.getEventTeamPhotos(this.data.id, team.id).subscribe(
      data => {
        // console.log(data.filter(photo => photo.teamId === team.id));
        if (data !== null) {
          this.showedPhotos = data.filter(photo => photo.teamId === team.id);
        }
      },
      error => {
        this.messageService.add(error);
      },
      () => {
      }
    );
  }

  updateEvent(): void {
    this.eventService.getEvent(this.data.id).subscribe(
      data => {
        this.data = data;
      },
      error => {
        this.messageService.add(error);
      },
      () => {
      }
    );
  }

  /**
   * Resets showed photos array when team item collapsed
   */
  collapsedTeam(): void {
    this.showedPhotos = null;
  }

  /**
   * Calculates team's progress in percents for a progress bar
   * @param team Team
   */
  calculateProgressPercent(team: Team): number {
    // console.log(team);
    return (team.checkedCheckpoints.length / this.data.checkpointCount) * 100;
  }

}
