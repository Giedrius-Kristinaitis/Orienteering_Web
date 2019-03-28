import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventDetailScreenComponent } from './event-detail-screen.component';

describe('EventDetailScreenComponent', () => {
  let component: EventDetailScreenComponent;
  let fixture: ComponentFixture<EventDetailScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventDetailScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventDetailScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
