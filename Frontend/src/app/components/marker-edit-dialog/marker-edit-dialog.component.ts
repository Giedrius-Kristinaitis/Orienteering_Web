import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Marker} from '../marker';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-marker-edit-dialog',
  templateUrl: './marker-edit-dialog.component.html',
  styleUrls: ['./marker-edit-dialog.component.css']
})
export class MarkerEditDialogComponent implements OnInit {

  markerName = new FormControl('', [Validators.required, Validators.minLength(1)]);

  constructor(public dialogRef: MatDialogRef<MarkerEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Marker) {

  }

  ngOnInit() {
  }

  onSaveClick(): void {
    if (this.markerName.valid) {
      this.dialogRef.close(this.data);
    }
  }

  onDeleteClick(): void {
    this.dialogRef.close(true);
  }

}
