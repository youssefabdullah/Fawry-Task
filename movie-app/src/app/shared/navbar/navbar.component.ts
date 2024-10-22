import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isAdmin: boolean = false;  // Property to track if user is an admin

  checkUserRole() {
    const userRole = localStorage.getItem('userRole');  // Get the role from localStorage
    if (userRole === 'ADMIN') {
      this.isAdmin = true;  // Set to true if the user is an admin
    }
  }
  constructor(private router: Router) {
    this.checkUserRole();
  }

  logout() {
    // Clear local storage or any other storage used for storing the user's session
    localStorage.clear();

    // Navigate to the login page after logging out
    this.router.navigate(['/login']);
}
}
