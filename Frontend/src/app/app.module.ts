import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule} from "@angular/platform-browser/animations";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { EventListComponent } from './components/event-list/event-list.component';

import {
  MatButtonModule,
  MatIconModule,
  MatListModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatFormFieldModule,
  MatInputModule,
  MatToolbarModule,
  MatCheckboxModule,
  MatCardModule,
  MatGridListModule,
  MatChipsModule
} from "@angular/material";
import { EventScreenComponent } from './components/event-screen/event-screen.component';
import {HttpClientModule} from "@angular/common/http";
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {FormsModule} from "@angular/forms";
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import { EventDetailScreenComponent } from './components/event-detail-screen/event-detail-screen.component';
import { MapComponent } from './components/map/map.component';
import { AgmCoreModule } from '@agm/core';
import { CreateEventScreenComponent } from './create-event-screen/create-event-screen.component';
import { EventCreateScreenComponent } from './components/event-create-screen/event-create-screen.component';
import { EventCreateComponent } from './components/event-create/event-create.component';
import { EventCreateMapComponent } from './components/event-create-map/event-create-map.component';

@NgModule({
  declarations: [AppComponent, EventListComponent, EventScreenComponent, NavigationBarComponent, EventDetailComponent, EventDetailScreenComponent, MapComponent, CreateEventScreenComponent, EventCreateScreenComponent, EventCreateComponent, EventCreateMapComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    HttpClientModule,
    MatToolbarModule,
    MatCheckboxModule,
    MatCardModule,
    MatGridListModule,
    MatChipsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBPxPFR9Pe5KDXNXX3RTXkWqE0zYhqF1uA'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
