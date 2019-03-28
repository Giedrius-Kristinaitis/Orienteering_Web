import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  lat: number = 54.687157;
  lng: number = 25.279652;
  constructor() { }

  ngOnInit() {
  }

}
