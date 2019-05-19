import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTeamCreateDialogComponent } from './event-team-create-dialog.component';

describe('EventTeamCreateDialogComponent', () => {
  let component: EventTeamCreateDialogComponent;
  let fixture: ComponentFixture<EventTeamCreateDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventTeamCreateDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventTeamCreateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
