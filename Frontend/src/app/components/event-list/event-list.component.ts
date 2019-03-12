import {Component, OnInit, ViewChild} from '@angular/core';
import { Event } from '../event';
import { EventService } from '../../services/event.service';
import { MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import {Router} from '@angular/router';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  events: Event[];
  dataSource: MatTableDataSource<Event>;
  displayedColumns: string[] = ['name', 'teamSize', 'checkpointCount', 'created'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private eventService: EventService, private router: Router) { }

  ngOnInit() {
    this.getEvents();
    this.dataSource = new MatTableDataSource<Event>(this.events);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  /**
   * Gets all events data
   */
  getEvents(): void {
    this.eventService.getEvents().subscribe(events => this.events = events);
  }

  /**
   * Event search
   * @param filterValue search word
   */
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.dataSource.filterPredicate = (data, filter: string): boolean => (data.name.toLowerCase().includes(filter) ||
      data.teamSize.toString().includes(filter) ||
      data.checkpointCount.toString().includes(filter) ||
      data.created.includes(filter));

    if(this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  /**
   * Shows alert with selected table row name
   * @param row selected table row
   */
  showEventDetails(row): void {
    alert(row['name']);
    this.router.navigateByUrl('/test');
  }
}

