import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EventScreenComponent} from "./components/event-screen/event-screen.component";
import {EventDetailScreenComponent} from "./components/event-detail-screen/event-detail-screen.component";

const routes: Routes = [
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: 'events', component: EventScreenComponent },
  { path: 'event/detail/:id', component: EventDetailScreenComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
