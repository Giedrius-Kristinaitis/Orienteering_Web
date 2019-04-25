import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {UserService} from "./services/user.service";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private userService: UserService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot):
    boolean {
    //console.log('Tikrinimas');
    //console.log(this.userService.getCurrentUser());
    if (this.userService.getCurrentUser() == undefined) {
      //console.log('Neprisijunges');
      this.router.navigate(['login']);

      return false;
    }

    return true;
  }

}
