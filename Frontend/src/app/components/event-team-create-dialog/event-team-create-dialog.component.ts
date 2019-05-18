import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {MarkerEditDialogComponent} from '../marker-edit-dialog/marker-edit-dialog.component';
import {Event} from '../event';
import {FormControl, Validators} from '@angular/forms';
import {EventService} from '../../services/event.service';
import {UserService} from '../../services/user.service';
import {MessageService} from '../../services/message.service';

@Component({
  selector: 'app-event-team-create-dialog',
  templateUrl: './event-team-create-dialog.component.html',
  styleUrls: ['./event-team-create-dialog.component.css']
})
export class EventTeamCreateDialogComponent implements OnInit {
  name: string;
  teamName = new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(20)]);

  constructor(public dialogRef: MatDialogRef<MarkerEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Event,
              private eventService: EventService,
              private userService: UserService,
              private messageService: MessageService) {
  }

  ngOnInit() {
  }

  /**
   * Adds team and current user to a new team
   */
  onCreateClick() {
    if (this.teamName.valid) {
      this.eventService.createTeam(
        this.data.id,
        {
          name: this.name,
          members: [this.userService.getCurrentUser()],
          id: null
        }
      ).subscribe(
        data => {
          this.data.teams.push(data);
        },
        error => {
          this.messageService.clear();
          this.messageService.add(error);
        },
        () => {
          this.dialogRef.close(true);
        }
      );
    }
  }

  /**
   * Closes team create dialog after pressing Cancel
   */
  onCancelClick() {
    this.dialogRef.close(false);
  }

}
