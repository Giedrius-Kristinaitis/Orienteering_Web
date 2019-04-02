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
  MatChipsModule, MatProgressSpinnerModule, MatError, MatDatepickerModule, MatNativeDateModule
} from "@angular/material";
import { EventScreenComponent } from './components/event-screen/event-screen.component';
import {HttpClientModule} from "@angular/common/http";
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { EventDetailComponent } from './components/event-detail/event-detail.component';
import { MapComponent } from './components/map/map.component';
import { AgmCoreModule } from '@agm/core';
import { EventCreateScreenComponent } from './components/event-create-screen/event-create-screen.component';
import { EventCreateComponent } from './components/event-create/event-create.component';
import { EventCreateMapComponent } from './components/event-create-map/event-create-map.component';
import {key} from "./mapApiKey";
import { LoginFormComponent } from './components/login-form/login-form.component';
import { LoginFormScreenComponent } from './components/login-register-screen/login-form-screen.component';

@NgModule({
  declarations: [AppComponent, EventListComponent, EventScreenComponent, NavigationBarComponent, EventDetailComponent, MapComponent, EventCreateScreenComponent, EventCreateComponent, EventCreateMapComponent, LoginFormComponent, LoginFormScreenComponent],
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
    MatProgressSpinnerModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    AgmCoreModule.forRoot({
      apiKey: key
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
