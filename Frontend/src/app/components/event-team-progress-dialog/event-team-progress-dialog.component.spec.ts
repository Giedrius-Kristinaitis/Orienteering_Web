import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTeamProgressDialogComponent } from './event-team-progress-dialog.component';

describe('EventTeamProgressDialogComponent', () => {
  let component: EventTeamProgressDialogComponent;
  let fixture: ComponentFixture<EventTeamProgressDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventTeamProgressDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventTeamProgressDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
