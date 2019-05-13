import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginRegisterScreenComponent } from './login-register-screen.component';

describe('LoginRegisterScreenComponent', () => {
  let component: LoginRegisterScreenComponent;
  let fixture: ComponentFixture<LoginRegisterScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginRegisterScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginRegisterScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
