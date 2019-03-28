import {Component, Input, OnInit} from '@angular/core';
import { Location } from "@angular/common";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  @Input() showGoBackButton: boolean;
  @Input() title: string;

  constructor(private location: Location) { }

  ngOnInit() {
  }

  goBack(event): void {
    this.location.back();
  }
}
