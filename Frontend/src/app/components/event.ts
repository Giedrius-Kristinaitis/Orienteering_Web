import {Checkpoint} from './checkpoint';
import {Team} from './team';
import {User} from './user';
import {Photo} from './photo';

export class Event {
  id: string;
  ownerId: string;
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
  owner: User;
  photos: Photo[];

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
    this.owner = event.owner;
    this.photos = event.photos;
  }
}
