import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventCreateScreenComponent } from './event-create-screen.component';

describe('EventCreateScreenComponent', () => {
  let component: EventCreateScreenComponent;
  let fixture: ComponentFixture<EventCreateScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventCreateScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventCreateScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
