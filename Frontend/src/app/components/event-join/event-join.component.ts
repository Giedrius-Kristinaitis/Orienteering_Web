import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {MarkerEditDialogComponent} from '../marker-edit-dialog/marker-edit-dialog.component';
import {Event} from '../event';
import {Team} from '../team';
import {EventService} from '../../services/event.service';
import {UserService} from '../../services/user.service';
import {User} from '../user';
import {MessageService} from '../../services/message.service';
import {EventTeamCreateDialogComponent} from '../event-team-create-dialog/event-team-create-dialog.component';

@Component({
  selector: 'app-event-join',
  templateUrl: './event-join.component.html',
  styleUrls: ['./event-join.component.css']
})
export class EventJoinComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<MarkerEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Event,
              private eventService: EventService,
              private userService: UserService,
              private messageService: MessageService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    // console.log(this.data);
  }

  /**
   * Adds user to selected team
   * @param team Selected team
   */
  onJoinClick(team: Team): void {
    // console.log('Metode: ' + this.userService.getCurrentUser());
    this.eventService.addTeamMember(this.data.id, team.id, this.userService.getCurrentUser()).subscribe(
      data => {
      },
      error => {
        this.messageService.clear();
        this.messageService.add(error);
      },
      () => {
        team.members.push(this.userService.getCurrentUser());
      }
    );
  }

  /**
   * Removes user from selected team
   * @param team Selected team
   */
  onLeaveClick(team: Team): void {
    this.eventService.removeTeamMember(this.data.id, team.id, this.userService.getCurrentUser().id).subscribe(
      data => {},
      error => {
        this.messageService.clear();
        this.messageService.add(error);
      },
      () => {
        team.members = team.members.filter(member => member.id !== this.userService.getCurrentUser().id);
      }
    );
  }

  /**
   * Hides dialog after cancel button press
   */
  onCancelClick(): void {
    this.dialogRef.close(false);
    // this.dialogRef.close(true);
  }

  /**
   * Opens create team dialog
   */
  onCreateClick(): void {
    const dialogRef = this.dialog.open(EventTeamCreateDialogComponent, {
      data: this.data
    });

    dialogRef.afterClosed().subscribe();
  }

  /**
   * Gets current user joined team object
   */
  getUserTeam(): Team {
    let foundTeam;
    const currentUser = this.userService.getCurrentUser();

    this.data.teams.forEach(team => {
      team.members.forEach(x => {
        if (x.id === currentUser.id) {
          foundTeam = team;
        }
      });
    });

    return foundTeam;
  }

}
