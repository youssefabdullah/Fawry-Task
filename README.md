## Fawry-Task
# Backend (Spring Boot)
- 1-Navigate to the backend directory:
   - cd fawry-backend
- 2-Configure your database
 - when to start project you start database using docker
- 3-Build the application using Maven:
  -  mvn clean install
- 4-Run the Spring Boot application:
   -  mvn spring-boot:run

# API Endpoints
Here is a list of main API endpoints provided by the Spring Boot backend:

- Method	Endpoint	Description
- Post	/admin/saveMovie/{title}	Save Movie
- Delete	/admin/deleteMovie/{id}	Delete Movie by ID
- POST	/login	Make login
- POST	/signup	Make signup
- GET	/user/getAllMovies	Featch data
# Front End (Angular)
- 1- Navigate to the frot end
  - cd movie-app
- 2 - Install Dependancy 
   - npm i
- 3- run project 
   - ng serve
