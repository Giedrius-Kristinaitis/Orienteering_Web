import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {MarkerEditDialogComponent} from '../marker-edit-dialog/marker-edit-dialog.component';
import {Event} from '../event';
import {Team} from '../team';
import {EventService} from '../../services/event.service';
import {UserService} from '../../services/user.service';
import {User} from '../user';

@Component({
  selector: 'app-event-join',
  templateUrl: './event-join.component.html',
  styleUrls: ['./event-join.component.css']
})
export class EventJoinComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<MarkerEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Event,
              private eventService: EventService,
              private userService: UserService) {
  }

  ngOnInit() {
    console.log(this.data);
  }

  onJoinClick(team: Team): void {
    console.log('Metode: ' + this.userService.getCurrentUser());
    this.eventService.addTeamMember(this.data.id, team.id, this.userService.getCurrentUser()).subscribe(
      data => {
      },
      error => {
      },
      () => {
        team.members.push(this.userService.getCurrentUser());
      }
    );
  }

  onCancelClick(): void {
    this.dialogRef.close(false);
    // this.dialogRef.close(true);
  }

  onCreateClick(): void {

  }

  getUserTeam(): boolean {
    let foundTeam = undefined;
    const currentUser = this.userService.getCurrentUser();
    this.data.teams.forEach(team => {
      team.members.forEach( x => {
        if (x.id === currentUser.id) {
          foundTeam = team;
        }
      });
    });

    return foundTeam;
  }

}
