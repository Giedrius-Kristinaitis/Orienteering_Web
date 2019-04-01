import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  email = new FormControl('', [Validators.required, Validators.minLength(6),  Validators.pattern('[a-zA-Z0-9_.]+@[a-zZ-z0-9_]+(\\.[a-zA-Z]+)+')]);
  password = new FormControl('', [Validators.required, Validators.minLength(5)]);

  constructor() { }

  ngOnInit() {
  }

  getErrorMessage(form: FormControl): string {
    switch (form) {
      case this.email: {
        return form.hasError('required') ? 'You must enter an email' :
          form.hasError('minlength') ? 'Email must bė at least 6 characters long' :
            form.hasError('pattern') ? 'You must enter a valid email' : '';
      }
      case this.password: {
        return form.hasError('required') ? 'You muest enter a password' :
          form.hasError('minlength') ? 'Password must be at least 5 characters long' : '';
      }
    }
  }

}
