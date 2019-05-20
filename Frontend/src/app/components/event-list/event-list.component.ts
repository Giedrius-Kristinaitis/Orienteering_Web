import {Component, OnInit, ViewChild} from '@angular/core';
import {Event} from '../event';
import {EventService} from '../../services/event.service';
import {MatPaginator, MatSort, MatTableDataSource, PageEvent} from '@angular/material';
import {Router} from '@angular/router';
import {MessageService} from '../../services/message.service';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  // Paginator's variables
  pageEvent: PageEvent;
  pageIndex: number;
  pageSize: number;
  length: number;

  searchInput: string;
  showCompleted = false;
  deletingEvent = false;
  events: Event[];

  dataSource: MatTableDataSource<Event>;
  displayedColumns: string[] = ['name', 'description', 'teamSize', 'checkpointCount', 'created', 'status', 'delete'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private eventService: EventService, private router: Router, private messageService: MessageService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.messageService.clear();
    this.getServerData(null);
    // this.dataSource.paginator = this.paginator;
  }

  editEvent(event): void {
    this.router.navigateByUrl(`/event/edit/${event.id}`);
  }

  /**
   * Deletes event after delete button click
   * @param event Click event
   */
  deleteEvent(event): void {
    this.deletingEvent = true;
    this.eventService.deleteEvent(event.id).subscribe(
      data => {
        this.dataSource.data.filter(x => x.id !== event.id);
        window.location.reload();
      },
      error => {
        this.messageService.add(error.toString());
      }
    );

  }

  /**
   * Event search
   * @param filterValue search word
   */
  applyFilter(filterValue: string = ' ', showCompleted: boolean) {
    if (this.dataSource === undefined) {
      return;
    }

    // Filters each event by a name and based on showCompleted value shows closed events
    this.dataSource.filterPredicate = (data, filter) => {
      return (showCompleted ? true : data.status.includes('Closed') === false) &&
        (filter === ' ' || data.name.toLowerCase().includes(filter.toLowerCase()));
    };
    this.dataSource.filter = filterValue;

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  /**
   * Events data loading for paginator
   * @param event Page event
   */
  public getServerData(event?: PageEvent) {
    this.eventService.getEvents((event === null ? 0 : event.pageIndex), (event === null ? 10 : event.pageSize)).subscribe(
      data => {
        this.dataSource = new MatTableDataSource<Event>(data.events);
        this.pageIndex = (event === null ? 0 : event.pageIndex);
        this.pageSize = data.pageSize;
        this.length = data.totalElements;

        data.events.forEach(x => console.log('Owner: ' + x.owner.id));

        // this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.applyFilter(this.searchInput, false);
      });
    return event;
  }

  /**
   * Shows alert with selected table row name
   * @param row selected table row
   */
  showEventDetails(row): void {
    if (!this.deletingEvent) {
      this.router.navigateByUrl(`/event/detail/${row.id}`);
    }
  }
}

