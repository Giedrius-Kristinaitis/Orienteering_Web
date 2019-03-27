import {Component, OnInit, ViewChild} from '@angular/core';
import { Event } from '../event';
import { EventService } from '../../services/event.service';
import {MatPaginator, MatTableDataSource, MatSort, PageEvent} from '@angular/material';
import {Router} from '@angular/router';
import {EventResponse} from "../eventResponse";
import {EVENTS} from "../mock-events";
import {ifTrue} from "codelyzer/util/function";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  searchInput: string;
  showCompleted: boolean = false;
  events: Event[];

  dataSource: MatTableDataSource<Event>;
  displayedColumns: string[] = ['name', 'teamSize', 'checkpointCount', 'created'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private eventService: EventService, private router: Router) { }

  ngOnInit() {
    // this.getEvents();
    this.events = EVENTS;
    this.dataSource = new MatTableDataSource<Event>(EVENTS);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.applyFilter(this.searchInput, false);
  }

  /**
   * Gets all events data
   */
  getEvents(): void {
    this.eventService.getEvents().subscribe(eventRes => {
      this.dataSource = new MatTableDataSource<Event>(eventRes.events);
    });
  }

  /**
   * Event search
   * @param filterValue search word
   */
  applyFilter(filterValue: string = ' ', showCompleted: boolean) {
    if(this.dataSource == undefined)
      return;

    //Filters each event by a name and based on showCompleted value shows closed events
    this.dataSource.filterPredicate = (data, filter) => {
      return (showCompleted ? true : data.status.includes('Closed') == false) && (filter == ' ' || data.name.includes(filter));
    }
    this.dataSource.filter = filterValue;

    if(this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  test(evens: any): void {
    console.log(evens);
  }

  /**
   * Shows alert with selected table row name
   * @param row selected table row
   */
  showEventDetails(row): void {
    alert(row['name']);
    this.router.navigateByUrl('/test');
  }

  onPaginateChange(event: PageEvent) {
    console.log(event);
  }
}

