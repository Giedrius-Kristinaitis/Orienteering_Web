import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EventScreenComponent} from './components/event-screen/event-screen.component';
import {AuthGuard} from './auth.guard';
import {LoginRegisterScreenComponent} from './components/login-register-screen/login-register-screen.component';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'events', component: EventScreenComponent, canActivate: [AuthGuard]},
  {path: 'event/detail/:id', component: EventScreenComponent, canActivate: [AuthGuard]},
  {path: 'event/create', component: EventScreenComponent, canActivate: [AuthGuard]},
  {path: 'event/edit/:id', component: EventScreenComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginRegisterScreenComponent },
  {path: 'register', component: LoginRegisterScreenComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
