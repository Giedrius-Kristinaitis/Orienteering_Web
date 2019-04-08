import {Component, Input, OnInit} from '@angular/core';
import {Checkpoint} from "../checkpoint";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  @Input() checkpoints: Checkpoint[];
  constructor() { }

  ngOnInit() {
  }

}
