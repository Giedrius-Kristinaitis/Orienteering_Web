import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EventScreenComponent} from './components/event-screen/event-screen.component';
import {LoginFormScreenComponent} from './components/login-register-screen/login-form-screen.component';
import {AuthGuard} from './auth.guard';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'events', component: EventScreenComponent, canActivate: [AuthGuard]},
  {path: 'event/detail/:id', component: EventScreenComponent, canActivate: [AuthGuard]},
  {path: 'event/create', component: EventScreenComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginFormScreenComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
