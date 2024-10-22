import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [FormsModule,CommonModule,HttpClientModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit{
  movieTitle: string = '';  // Model for the movie title input
  message: string | null = null;  // To display success or error message
  isSuccess: boolean = true;  // To toggle between success and error message styles

  constructor(private router: Router, private http: HttpClient) {
    this.checkUserRole();
  }

  checkUserRole() {
    // const userRole = localStorage.getItem('userRole');
    //  //console.log(userRole)
    // if (userRole != 'ADMIN') {
    //   // Navigate to 404 page if the role is not 'admin'
    //   this.router.navigate(['/404']);
    // }
  }

  onSubmit() {
    this.saveMovie(this.movieTitle);  // Call saveMovie method when the form is submitted
  }

  // Method to save movie by title for admin users
  saveMovie(title: string) {
    const token = localStorage.getItem('accessToken'); // Get token from local storage

    if (token && title) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`,  // Add the token to the Authorization header
        'Content-Type': 'application/json'  // Set the content type to JSON
      });

      // Make the POST request to save the movie
      this.http.post(`http://localhost:8080/admin/saveMovie/${title}`, {}, { headers })
        .subscribe(
          response => {
            this.message = 'Movie saved successfully!';
            this.isSuccess = true;  // Set to true for success alert
            this.movieTitle = '';  // Clear the input field after success
          },
          error => {
            this.message = 'Error saving movie: ' + error.message;
            this.isSuccess = false;  // Set to false for error alert
          }
        );
    } else {
      this.message = 'No token found or invalid movie title';
      this.isSuccess = false;
    }
  }
  movies: any[] = [];  // Store the movies list
  message1: string | null = null;  // Message1 to show success or error
  isSuccess1: boolean = true;  // To toggle success or error



  ngOnInit(): void {
    this.getAllMovies();  // Fetch all movies when the component loads
  }

  // Fetch all movies from the API
  getAllMovies() {
    const token = localStorage.getItem('accessToken');  // Get the token from local storage

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`  // Attach the Authorization token
      });

      // Fetch movies from the API
      this.http.get<any[]>('http://localhost:8080/user/getAllMovies', { headers }).subscribe(
        (response) => {
          this.movies = response;  // Store the movies in the array
        },
        (error) => {
          console.error('Error fetching movies:', error);
          this.message1 = 'Error fetching movies.';
          this.isSuccess1 = false;
        }
      );
    } else {
      this.message1 = 'No token found';
      this.isSuccess1 = false;
    }
  }

  // Delete a movie by ID
  deleteMovie(id: number) {
    const token = localStorage.getItem('accessToken');  // Get the token from local storage

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`  // Attach the Authorization token
      });

      // Send DELETE request to delete the movie
      this.http.delete(`http://localhost:8080/admin/deleteMovie/${id}`, { headers }).subscribe(
        () => {
          this.message1 = `Movie with ID ${id} deleted successfully.`;
          this.isSuccess1 = true;

          // Remove the deleted movie from the table
          this.movies = this.movies.filter(movie => movie.id !== id);
        },
        (error) => {
          console.error('Error deleting movie:', error);
          this.message1 = 'Error deleting movie.';
          this.isSuccess1 = false;
        }
      );
    } else {
      this.message1 = 'No token found';
      this.isSuccess1 = false;
    }
  }
}
