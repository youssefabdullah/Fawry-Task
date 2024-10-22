import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent implements OnInit {
  movies: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchMovies();
  }

  fetchMovies(): void {
    const token = localStorage.getItem('accessToken') as string; // Cast token to string

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}` // Ensure token is passed as string
      });

      this.http.get<any[]>('http://localhost:8080/user/getAllMovies', { headers }).subscribe(
        (response) => {
          console.log(response)
          this.movies = response;
        },
        (error) => {
          console.error('Error fetching movies:', error);
        }
      );
    } else {
      console.error('No auth token found');
    }
  }
}
