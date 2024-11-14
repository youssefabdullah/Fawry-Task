import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  showNavbar = true;  // Control whether the navbar should be shown

  constructor(private router: Router) {
    // Subscribe to route changes to toggle navbar visibility
    this.router.events.subscribe(() => {
      this.checkRoute();
    });
  }

  // Check the current route and hide navbar on specific routes
  checkRoute(): void {
    const currentRoute = this.router.url;
    if (currentRoute === '/login') {
      this.showNavbar = false;  // Hide navbar on the login page
    }
    else if (currentRoute === '/signup') {
      this.showNavbar = false;  // Hide navbar on the login page
    }
    else {
      this.showNavbar = true;  // Show navbar on all other pages
    }
  }
}
