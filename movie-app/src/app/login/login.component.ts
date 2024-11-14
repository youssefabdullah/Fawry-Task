import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { RouterModule,Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule,RouterModule] // Add FormsModule here
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}
  async onSubmit() {
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: this.username, password: this.password })
      });

      const data = await response.json();

      if (data.responseCode === "200") {
        alert('Login successful! ');
        localStorage.setItem('accessToken', data.accessToken);
        localStorage.setItem('userRole', data.role);
        if (data.role === 'ADMIN') {
          this.router.navigate(['/admin-dashboard']); // Navigate to admin dashboard
        } else {
          this.router.navigate(['/user-dashboard']); // Navigate to user dashboard
        }
      } else {
        alert('Login failed: ' + data.message);
      }
    } catch (error) {
      alert('An error occurred: ' );
    }
  }
}
