import {Checkpoint} from "./checkpoint";
import {Team} from "./team";

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
}
