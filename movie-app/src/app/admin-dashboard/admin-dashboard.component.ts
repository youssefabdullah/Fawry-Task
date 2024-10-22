import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {
  constructor(private router: Router) {
    this.checkUserRole();
  }

  checkUserRole() {
    const userRole = localStorage.getItem('userRole');

    if (userRole !== 'admin') {
      // Navigate to 404 page if the role is not 'admin'
      this.router.navigate(['/404']);
    }
  }
}
