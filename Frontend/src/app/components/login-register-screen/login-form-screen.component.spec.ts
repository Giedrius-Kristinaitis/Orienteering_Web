import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginFormScreenComponent} from './login-form-screen.component';

describe('LoginFormScreenComponent', () => {
  let component: LoginFormScreenComponent;
  let fixture: ComponentFixture<LoginFormScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LoginFormScreenComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginFormScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
