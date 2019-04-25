import {Component, Input, OnInit} from '@angular/core';
import {Location} from "@angular/common";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  @Input() showGoBackButton: boolean;
  @Input() showUserIcons: boolean;
  @Input() title: string;

  constructor(private location: Location, private userService: UserService) {
  }

  ngOnInit() {
  }

  goBack(event): void {
    this.location.back();
  }

  logout(): void {
    this.userService.logout();
  }
}
