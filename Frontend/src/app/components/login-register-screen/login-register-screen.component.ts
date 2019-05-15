import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-register-screen',
  templateUrl: './login-register-screen.component.html',
  styleUrls: ['./login-register-screen.component.css']
})
export class LoginRegisterScreenComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

}
