import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router,RouterModule } from '@angular/router';

@Component({
  selector: 'app-singup',
  standalone: true,
  imports: [FormsModule,RouterModule],
  templateUrl: './singup.component.html',
  styleUrl: './singup.component.css'
})
export class SingupComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}

  async onSubmit() {
    try {
      const response = await fetch('http://localhost:8080/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: this.username, password: this.password })
      });

      const data = await response.json();

      if (data.responseCode === "200") {
        alert('Register successful! ');

          this.router.navigate(['/login']); // Navigate to admin dashboard

      } else {
        alert('Login failed: ' + data.message);
      }
    } catch (error) {
      alert('An error occurred: ' );
    }
  }
}
