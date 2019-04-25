import {Event} from "./event";

export class EventResponse {
  events: Event[];
  totalElements: number;
  pageSize: number;
  totalPages: number;
}
