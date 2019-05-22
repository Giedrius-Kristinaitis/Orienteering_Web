import {User} from './user';

export class Team {
  id: string;
  name: string;
  members: User[];
  checkedCheckpoints: string[];
}
