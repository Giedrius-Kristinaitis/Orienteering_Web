import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {
  name = new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(16)]);
  teamSize = new FormControl('', [Validators.required, Validators.pattern('^[0-9]+$'), Validators.min(1), Validators.max(30)]);
  date = new FormControl('', [Validators.required, Validators.min(new Date().getMilliseconds()), Validators.pattern('^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$')]);

  dateInput = new Date();
  getErrorMessage(form : FormControl) {
    switch(form) {
      case this.name: {
        return form.hasError('required') ? 'You must enter a name' :
          form.hasError('minlength') ? 'Must be at least 4 characters' :
          form.hasError('maxlength') ? 'Must not exceed 16 characters' : '';
      }
      case this.teamSize: {
        return form.hasError('required') ? 'You must enter a team size' :
          form.hasError('pattern') ? 'You must enter a number' :
            form.hasError('min') ? 'You must enter value higher or equal to 0' :
              form.hasError('max') ? 'You must enter a value lower or equal to 30' : '';
      }
      case this.date: {
        return form.hasError('required') ? 'You must select a date' :
          form.hasError('min') ? 'You can not select previous date' :
            form.hasError('pattern') ? 'You must enter a date' : '';
      }
    }
  }

  constructor() { }

  ngOnInit() {

  }

}
