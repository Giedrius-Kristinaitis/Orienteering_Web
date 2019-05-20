import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Marker} from '../marker';
import {MatDialog} from '@angular/material';
import {MarkerEditDialogComponent} from '../marker-edit-dialog/marker-edit-dialog.component';
import {Observable} from 'rxjs';
import {Event} from '../event';
import {google} from '@agm/core/services/google-maps-types';

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
  checkpointName = 1;
  @Input() event: Observable<Event>;
  @Output() checkpointsList: EventEmitter<Marker[]> = new EventEmitter<Marker[]>();

  constructor(public dialog: MatDialog) {
  }

  id: string;
  name: string;

  markers: Marker[] = [];
  centerLong: number;
  centerLat: number;

  /**
   * Adds existing markers from event
   */
  ngOnInit() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => this.setMapCoordsToUserLoc(position), error => {
        this.setMapCoordsToUserLoc({
          coords : [{
            latitude: 54.903362,
            longitude: 23.960036
          }]
        });
      });
    }

    if (this.event !== undefined) {
      this.event.subscribe(
        data => {
          data.checkpoints.forEach(x => this.saveMarker({
            name: x.name,
            coords: {lat: x.latitude, lng: x.longitude}
          }));
        }
      );
    }
  }

  setMapCoordsToUserLoc(position) {
    this.centerLat = position.coords.latitude;
    this.centerLong = position.coords.longitude;
  }

  /**
   * Adds marker to list
   * @param event Press event
   */
  saveMarker(event) {
    const newMarker = {
      latitude: event.coords.lat,
      longitude: event.coords.lng,
      id: this.checkpointName.toString(),
      name: event.name !== undefined ? event.name : this.checkpointName.toString()
    };

    this.checkpointName++;
    this.markers.push(newMarker);
    this.checkpointsList.emit(this.markers);
  }

  /**
   * Removes marker from list
   * @param marker Marker
   */
  deleteMarker(marker) {
    const lat = marker.latitude;
    const lng = marker.longitude;

    if (this.markers.length === 1) {
      this.centerLong = lat;
      this.centerLat = lng;
    }

    this.markers = this.markers.filter(mk => mk.id !== marker.id);
    this.checkpointsList.emit(this.markers);
    this.reorderMarkersIDs();
    this.checkpointName = this.markers.length + 1;
  }

  /**
   * Reorders markers id's
   */
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

  /**
   * Opens marker info dialog
   * @param marker Marker
   */
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
