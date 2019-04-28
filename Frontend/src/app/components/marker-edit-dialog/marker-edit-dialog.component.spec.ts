import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarkerEditDialogComponent } from './marker-edit-dialog.component';

describe('MarkerEditDialogComponent', () => {
  let component: MarkerEditDialogComponent;
  let fixture: ComponentFixture<MarkerEditDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarkerEditDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarkerEditDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
