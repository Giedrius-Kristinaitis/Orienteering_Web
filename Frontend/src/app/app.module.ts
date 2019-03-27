import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule} from "@angular/platform-browser/animations";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import { EventListComponent } from './components/event-list/event-list.component';

import { MatButtonModule, MatIconModule, MatListModule, MatTableModule, MatPaginatorModule, MatSortModule,
  MatFormFieldModule, MatInputModule, MatToolbarModule, MatCheckboxModule } from "@angular/material";
import { EventScreenComponent } from './components/event-screen/event-screen.component';
import {HttpClientModule} from "@angular/common/http";
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [AppComponent, EventListComponent, EventScreenComponent, NavigationBarComponent],
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
    MatCheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
