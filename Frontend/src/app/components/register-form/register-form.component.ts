import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {MessageService} from '../../services/message.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  static readonly minEmailLength = 6;
  static readonly maxEmailLength = 100;
  static readonly minNameLength = 1;
  static readonly maxNameLength = 100;
  static readonly minLastNameLength = 1;
  static readonly maxLastNameLength = 100;
  static readonly minPasswordLength = 5;
  static readonly maxPasswordLength = 100;

  user: User;
  input: FormGroup;

  constructor(private userService: UserService,
              private messageService: MessageService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  /**
   * Initiliazes form group for input validation
   */
  ngOnInit() {
    this.messageService.clear();
    this.user = new User();
    this.input = new FormGroup({
      email: new FormControl(this.user.email, [Validators.required, Validators.minLength(RegisterFormComponent.minEmailLength),
        Validators.maxLength(RegisterFormComponent.maxEmailLength), Validators.pattern('^.+@+.+$')]),
      firstName: new FormControl(this.user.firstName, [Validators.required, Validators.minLength(RegisterFormComponent.minNameLength),
        Validators.maxLength(RegisterFormComponent.maxNameLength)]),
      lastName: new FormControl(this.user.lastName, [Validators.required, Validators.minLength(RegisterFormComponent.minLastNameLength),
        Validators.maxLength(RegisterFormComponent.maxLastNameLength)]),
      password: new FormControl(this.user.password, [Validators.required, Validators.minLength(RegisterFormComponent.minPasswordLength),
        Validators.maxLength(RegisterFormComponent.maxPasswordLength)])
    });
  }

  /**
   * Validation error messages
   * @param form Error message
   */
  getErrorMessage(form: FormControl) {
    switch (form) {
      case this.input.get('email') : {
        return form.hasError('required') ? 'You must enter your email' :
          form.hasError('minLength') ? `Your email length must be equal or higher than ${RegisterFormComponent.minEmailLength}` :
            form.hasError('maxLength') ? `Your email length must be equal or lower than ${RegisterFormComponent.maxEmailLength}` :
              form.hasError('pattern') ? 'You must enter a valid email' : '';
      }
      case this.input.get('firstName') : {
        return form.hasError('required') ? 'You must enter your first name' :
          form.hasError('minLength') ? `Your first name length must be equal or higher than ${RegisterFormComponent.minNameLength}` :
            form.hasError('maxLength') ? `Your last name length must be equal or lower than ${RegisterFormComponent.maxNameLength}` : '';
      }
      case this.input.get('lastName') : {
        return form.hasError('required') ? 'You must enter your last name' :
          form.hasError('minLength') ? `Your last name length must be equal or higher than ${RegisterFormComponent.minLastNameLength}` :
            form.hasError('maxLength') ? `Your last name length must be equal or lower than ${RegisterFormComponent.maxLastNameLength}` :
              '';
      }
      case this.input.get('password') : {
        return form.hasError('required') ? 'You must enter a password' :
          form.hasError('minLength') ? `Your password length must be equal or higher than ${RegisterFormComponent.minPasswordLength}` :
            form.hasError('maxLength') ? `Your password length must be equal or lower than ${RegisterFormComponent.maxPasswordLength}` : '';
      }
    }
  }

  /**
   * Registers a new user
   */
  register() {
    if (this.input.valid) {
      this.userService.addUser(this.user).subscribe(
        data => {
        },
        error => {
          this.messageService.add(error.toString());
        },
        () => {
          this.authenticationService.login(this.user.email, this.user.password).subscribe(
            data => {
              this.router.navigate(['events']);
            },
            error => {
              this.messageService.add(error.toString());
              this.router.navigateByUrl('/login');
            });
        }
      );
    }
  }

}
