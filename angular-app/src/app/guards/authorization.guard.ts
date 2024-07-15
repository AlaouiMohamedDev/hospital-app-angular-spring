import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()

export class AuthrizationGuard {
  constructor(private authService : AuthenticationService,
    private router : Router
  ){

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    
    if(this.authService.authenticated){
      let requiredRoles =  route.data['roles'];
      let authUserRoles = this.authService.roles;
      for(let role of authUserRoles){
        if(requiredRoles.includes(role)){
          return true;
        }
      }
      return false;
    }
      this.router.navigateByUrl("/login");
      return false;
  }
}