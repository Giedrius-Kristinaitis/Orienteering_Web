import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {MarkerEditDialogComponent} from '../marker-edit-dialog/marker-edit-dialog.component';
import {Event} from '../event';

@Component({
  selector: 'app-event-join',
  templateUrl: './event-join.component.html',
  styleUrls: ['./event-join.component.css']
})
export class EventJoinComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<MarkerEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Event) { }

  ngOnInit() {
  }

  onJoinClick(): void {
    // if (this.markerName.valid) {
    //   this.dialogRef.close(this.data);
    // }
    this.dialogRef.close(true);
  }

  onCancelClick(): void {
    this.dialogRef.close(false);
    // this.dialogRef.close(true);
  }

}
