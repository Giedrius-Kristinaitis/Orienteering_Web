import {Component, Input, OnInit} from '@angular/core';
import { MatToolbar } from "@angular/material";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  @Input() title: string;

  constructor() { }

  ngOnInit() {
  }

}
