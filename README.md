Library Management System - Backend

This backend application is a RESTful API for a Library Management System. It streamlines operations for managing books, users, and their interactions. Designed using Java and Spring Boot, it provides robust endpoints for adding, updating, deleting, and retrieving books and users, along with book assignment.

Features:-

Book Management:-

Add, update, delete, and view books.
Track availability of multiple copies of a book.
You can delete only remaining copies of book from database.


User Management:-
Add, update, delete, and view users.


Book Assignment:-
Assign books to users and track their return dates.
Retrieve book assignment history with timestamps.



Modular Design:-
Layered architecture with separation of Controller, Service, and Repository layers.


Technologies Used:- 

Language: Java

Framework: Spring Boot

Database: MySQL/PostgreSQL (configure in application.properties)

ORM: Spring Data JPA

API Standardization: ResponseEntity for consistent HTTP responses

Setup Instructions

Prerequisites
Java 17+ installed
Maven 3.8+ installed
MySQL/PostgreSQL database instance


Steps to Run
Clone the repository:
bash
Copy
Edit
git clone <repository-url>
cd library-management-system-backend
Configure the database:
Update the application.properties file located in src/main/resources with your database credentials:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/library_db  
spring.datasource.username=<your-username>  
spring.datasource.password=<your-password>  
Build and run the application:

bash
Copy
Edit
mvn clean install
mvn spring-boot:run
Access the API documentation or test endpoints:

Default port: http://localhost:8080
Use Postman or Swagger for API testing.


API Endpoints

Books

Update a Book: PUT /manager/{managerId}/updateBook/{bookId}

Delete a Book: DELETE /manager/{managerId}/book/{bookId}

Get All Books: GET /manager/{managerId}/books

Add a Book: POST /manager/{managerId}/book


Users

Add a User: POST /manager/{managerId}/users

Update a User: PUT /manager/{managerId}/updateUser/{userId}

Delete a User: DELETE /manager/{managerId}/user/{userId}

Get All Users: GET /manager/{managerId}/users


Assignments

Assign a Book to User: POST /manager/{managerId}/assign

Retrieve a Book from User: POST /manager/{managerId}/return


Contributing
Contributions are welcome! Please follow these steps:

Fork the repository.
Create a feature branch.
Commit your changes.
Submit a pull request.


NOTE : Firstly you have to add manager in your database then you perform some another operations.
