import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import * as moment from 'moment';
import {EventService} from '../../services/event.service';
import {MessageService} from '../../services/message.service';
import {Router} from '@angular/router';
import {Marker} from '../marker';

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {
  readonly minTeamLimit = 1;
  readonly maxTeamLimit = 100;
  readonly minNameLength = 4;
  readonly maxNameLength = 16;
  readonly minDistance = 1;
  readonly maxDistance = 1_000_000;
  readonly minDuration = 1;
  readonly maxDuration = 1_000_000;
  readonly minDescriptionLength = 1;
  readonly maxDescriptionLength = 200;
  readonly minCheckpoints = 1;
  readonly maxCheckpoints = 100;

  eventForm: FormGroup;
  submitted = false;

  minDate: Date;
  maxDate: Date;

  checkpointsCount = 0;
  checkpointsList: Marker[] = [];

  constructor(private eventService: EventService,
              private messageService: MessageService,
              private router: Router) {
  }

  ngOnInit() {
    // // Date limits for data picker input
    this.minDate = new Date(Date.now());
    this.maxDate = new Date(new Date().setFullYear(new Date().getFullYear() + 1));

    this.eventForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(this.minNameLength),
        Validators.maxLength(this.maxNameLength)]),
      teamSize: new FormControl('', [Validators.required, Validators.pattern('^[0-9]+$'),
        Validators.min(this.minTeamLimit), Validators.max(this.maxTeamLimit)]),
      date: new FormControl('', [Validators.required, Validators.min(new Date().getMilliseconds())]),
      time: new FormControl('', [Validators.required, Validators.pattern('^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$')]),
      duration: new FormControl('', [Validators.required, Validators.pattern('^[0-9]+$'),
        Validators.min(this.minDuration), Validators.max(this.maxDuration)]),
      distance: new FormControl('', [Validators.required, Validators.pattern('^[0-9]+$'),
        Validators.min(this.minDistance), Validators.max(this.maxDistance)]),
      description: new FormControl('', [Validators.required,
        Validators.minLength(this.minDescriptionLength), Validators.maxLength(this.maxDescriptionLength)]),
      checkpointsCount: new FormControl(this.checkpointsCount, [Validators.min(this.minCheckpoints),
        Validators.max(this.maxCheckpoints)])
    });
  }

  getErrorMessage(form: FormControl) {
    switch (form) {
      case this.eventForm.get('name'): {
        return form.hasError('required') ? 'You must enter a name' :
          form.hasError('minlength') ? 'Must be at least ' + this.minNameLength + ' characters' :
            form.hasError('maxlength') ? 'Must not exceed ' + this.maxNameLength + ' characters' : '';
      }
      case this.eventForm.get('teamSize'): {
        return form.hasError('required') ? 'You must enter a team size' :
          form.hasError('pattern') ? 'You must enter a number' :
            form.hasError('min') ? 'You must enter value higher than 0' :
              form.hasError('max') ? 'You must enter a value lower or equal to ' + this.maxTeamLimit : '';
      }
      case this.eventForm.get('date'): {
        // return console.log(form);
        return form.hasError('required') ? 'You must select a date' :
          form.hasError('matDatepickerMin') ? 'You  can not select previous date' :
            form.hasError('matDatepickerMax') ? 'You must select date within a year' : '';
      }
      case this.eventForm.get('time'): {
        return form.hasError('required') ? 'You must select event starting time' :
          form.hasError('pattern') ? 'You must enter time in correct format' : '';
      }
      case this.eventForm.get('duration'): {
        return form.hasError('pattern') ? 'You must enter a valid number' :
          form.hasError('min') ? 'Number must be equal or higher than ' + this.minDuration :
            form.hasError('max') ? 'Number must be equal or lower than ' + this.maxDuration :
              form.hasError('required') ? 'You must enter a number' : '';
      }
      case this.eventForm.get('distance'): {
        return form.hasError('pattern') ? 'You must enter a valid numbr' :
          form.hasError('min') ? 'Number must be equal or higher than ' + this.minDistance :
            form.hasError('max') ? 'Number must be equal or lower than ' + this.maxDistance :
              form.hasError('required') ? 'You must enter a number' : '';
      }
      case this.eventForm.get('description'): {
        return form.hasError('required') ? 'You must write something' :
          form.hasError('minLength') ? 'Text length must be equal or highter than ' + this.minDescriptionLength :
            form.hasError('maxLength') ? 'Text length must be equal or lower than' + this.maxDescriptionLength : '';
      }
      case this.eventForm.get('checkpointsCount') : {
        return form.hasError('min') ? 'You must at least add ' + this.minCheckpoints +
          (this.minCheckpoints === 1 ? ' checkpoint' : 'checkpoints') :
          form.hasError('max') ? 'Checkpoints count must be equal or lower than ' + this.maxCheckpoints : '';
      }
    }
  }

  onSubmit(): void {
    this.submitted = true;
    this.eventForm.get('checkpointsCount').updateValueAndValidity();
    console.log(this.eventForm.get('checkpointsCount').value)

    if (this.eventForm.valid) {
      const time = moment(this.eventForm.get('time').value, 'HH:mm');
      const startingDate = time.hour() * 3_600_000 + time.minutes() * 60_000 + this.eventForm.get('date').value.valueOf();
      const estimatedDuration = this.eventForm.get('duration').value * 3_600_000;

      const newEvent = {
        name: this.eventForm.get('name').value,
        teamSize: this.eventForm.get('teamSize').value,
        starting: new Date(startingDate).toJSON(),
        estimatedTimeMillis: estimatedDuration,
        estimatedDistanceMetres: this.eventForm.get('distance').value,
        description: this.eventForm.get('description').value,
        checkpointCount: this.checkpointsCount,
        checkpoints: this.checkpointsList
      };

      this.eventService.addEvent(JSON.stringify(newEvent)).subscribe(
        date => {
        },
        error => this.messageService.add(error.toString()),
        () => {
          this.router.navigateByUrl('/events');
        }
      );
      this.messageService.clear();
    }
  }

  getCheckpoints(list: Marker[]): void {
    this.checkpointsList = list;
    this.checkpointsCount = list.length;
    this.eventForm.get('checkpointsCount').setValue(this.checkpointsCount);
    this.eventForm.get('checkpointsCount').updateValueAndValidity();
  }
}
