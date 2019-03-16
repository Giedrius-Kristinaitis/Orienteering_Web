export class Event {
  id: number;
  name: string;
  description: string;
  checkpointCount: number;
  checkpoints: number[];
  teamSize: number;
  teams: number[];
  created: string;
  starting: string;
  status: string;
  estimatedTime: number;
  estimatedDistance: number;
}
