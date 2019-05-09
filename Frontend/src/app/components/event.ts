import {Checkpoint} from './checkpoint';
import {Team} from './team';

export class Event {
  id: number;
  name: string;
  description: string;
  checkpointCount: number;
  checkpoints: Checkpoint[];
  teamSize: number;
  teams: Team[];
  created: string;
  starting: string;
  status: string;
  estimatedTimeMillis: number;
  estimatedDistanceMetres: number;

  constructor(event: Event) {
    if (event === null) {
      return;
    }

    this.id = event.id;
    this.name = event.name;
    this.description = event.description;
    this.checkpointCount = event.checkpointCount;
    this.checkpoints = event.checkpoints;
    this.teamSize = event.teamSize;
    this.teams = event.teams;
    this.created = event.created;
    this.starting = event.starting;
    this.status = event.status;
    this.estimatedTimeMillis = event.estimatedTimeMillis;
    this.estimatedDistanceMetres = event.estimatedDistanceMetres;
  }
}
