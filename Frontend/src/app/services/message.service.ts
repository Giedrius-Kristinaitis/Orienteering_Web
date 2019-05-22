import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  public messages: string[] = [];

  add(message: string) {
    this.clear();
    this.messages.push(message);
  }

  get() {
    return this.messages.pop();
  }

  clear() {
    this.messages = [];
  }

  count() {
    return this.messages.length;
  }
}
