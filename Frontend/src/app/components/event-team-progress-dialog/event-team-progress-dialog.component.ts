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
  static readonly updateInterval = 3000;
  showedPhotos: Photo[];

  constructor(public dialogRef: MatDialogRef<MarkerEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Event,
              private eventService: EventService,
              private userService: UserService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.messageService.clear();
    this.showedPhotos = null;
    // console.log(this.data);

    interval(EventTeamProgressDialogComponent.updateInterval).subscribe(
      () => {
        this.updateEvent();
        console.log('Updated');
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
    this.showedPhotos = this.data.photos.filter(x => x.teamId === team.id).sort((a, b) => {
      if (+a.checkpointId > +b.checkpointId) {
        return 1;
      }
      if (+a.checkpointId < +b.checkpointId) {
        return -1;
      }
      return 0;
    });
    // this.showedPhotos.forEach(x => console.log(x));
    // console.log('Masyv: ' + this.showedPhotos[0].downloadURL);
  }

  updateEvent(): void {
    this.eventService.getEvent(this.data.id).subscribe(
      data => {
        if (data.photos.length !== this.showedPhotos.length) {
          console.log('Yra nauju ft');
          data.photos.forEach(photo => {
            if (!this.showedPhotos.find(x => x.checkpointId === photo.checkpointId)) {
              this.data.teams.filter(x => x.id === photo.teamId)[0].checkedCheckpoints.push(photo.checkpointId);
              this.showedPhotos.push(photo);
            }
          });

          this.showedPhotos = this.showedPhotos.sort((a, b) => {
            if (+a.checkpointId > +b.checkpointId) {
              return 1;
            }
            if (+a.checkpointId < +b.checkpointId) {
              return -1;
            }
            return 0;
          });
        }
        // data.photos = this.showedPhotos;
        // this.data = data;
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
