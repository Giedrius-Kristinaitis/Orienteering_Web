import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventCreateMapComponent } from './event-create-map.component';

describe('EventCreateMapComponent', () => {
  let component: EventCreateMapComponent;
  let fixture: ComponentFixture<EventCreateMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventCreateMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventCreateMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
