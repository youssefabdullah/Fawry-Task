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
    const token = localStorage.getItem('accessToken') as string;

    if (token) {
      fetch('http://localhost:8080/user/getAllMovies', {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
      .then(response => response.json())
      .then(data => {
        console.log('Movies:', data);
        this.movies = data;
      })
      .catch(error => {
        console.error('Error fetching movies:', error);
      });
    } else {
      console.error('No auth token found');
    }
  } 
}
