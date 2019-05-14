import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Marker} from '../marker';
import {MatDialog} from '@angular/material';
import {MarkerEditDialogComponent} from '../marker-edit-dialog/marker-edit-dialog.component';

export interface DialogData {
  id: string;
  name: string;
}

@Component({
  selector: 'app-event-create-map',
  templateUrl: './event-create-map.component.html',
  styleUrls: ['./event-create-map.component.css']
})
export class EventCreateMapComponent implements OnInit {
  static checkpointName = 1;
  @Output() checkpointsList: EventEmitter<Marker[]> = new EventEmitter<Marker[]>();

  constructor(public dialog: MatDialog) {
  }

  id: string;
  name: string;

  markers: Marker[] = [];
  centerLong = 30;
  centerLat = 30;

  ngOnInit() {
  }

  saveMarker(event) {
    const newMarker = {
      latitude: event.coords.lat,
      longitude: event.coords.lng,
      id: EventCreateMapComponent.checkpointName.toString(),
      name: EventCreateMapComponent.checkpointName.toString()
    };

    EventCreateMapComponent.checkpointName++;
    this.markers.push(newMarker);
    this.checkpointsList.emit(this.markers);
  }

  deleteMarker(marker) {
    this.markers = this.markers.filter(mk => mk.id !== marker.id);
    this.checkpointsList.emit(this.markers);
    this.reorderMarkersIDs();
    EventCreateMapComponent.checkpointName = this.markers.length + 1;
  }

  reorderMarkersIDs() {
    let counter = 1;
    this.markers.forEach(marker => {
      if (marker.id === marker.name) {
        marker.name = counter.toString();
      }

      marker.id = counter.toString();
      counter++;
    });
  }

  editMarker(marker: Marker) {
    this.openDialog(marker);
  }

  openDialog(marker: Marker): void {
    const dialogRef = this.dialog.open(MarkerEditDialogComponent, {
      width: '250px',
      data: {name: marker.name, id: marker.id, latitude: marker.latitude, longitude: marker.longitude}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.deleteMarker(marker);
      } else if (typeof result === 'object') {
        marker.name = result.name;
      }
    });
  }
}
