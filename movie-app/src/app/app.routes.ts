import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SingupComponent } from './singup/singup.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { AdminGuard } from './admin.guard';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { NgModule } from '@angular/core';

 export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SingupComponent },
  { path: 'admin-dashboard' ,loadComponent: () => import('./admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent), canActivate: [AdminGuard]},
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: '', component: UserDashboardComponent },
  //{ path: '**', redirectTo: 'login' } ,
  { path: '404', loadComponent: () => import('./page-not-found/page-not-found.component').then(m => m.PageNotFoundComponent)  }// Redirect all other routes to login
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
