Library Management System API
Overview
The Library Management System API, developed using Spring Boot, enables librarians to manage books, patrons, and borrowing records efficiently. This RESTful API supports CRUD operations and ensures data integrity through transaction management.

Getting Started
Prerequisites
Java 17
Maven
Any relational database (e.g., H2, MySQL, PostgreSQL) set up and configured
Installation
Clone the repository:
git clone https://github.com/[your-username]/library-management.git
Navigate to the project directory:
cd library-management
Build the project using Maven:
mvn clean install
Running the Application
Launch the application by running:
java -jar target/library-management-0.0.1-SNAPSHOT.jar
Alternatively, run the application directly from your IDE.

API Endpoints
Books
GET /api/books: Retrieves a list of all books.
GET /api/books/{id}: Retrieves a specific book by ID.
POST /api/books: Adds a new book (Provide JSON body).
PUT /api/books/{id}: Updates an existing book by ID (Provide JSON body).
DELETE /api/books/{id}: Removes a book by ID.
Patrons
GET /api/patrons: Retrieves a list of all patrons.
GET /api/patrons/{id}: Retrieves a specific patron by ID.
POST /api/patrons: Adds a new patron (Provide JSON body).
PUT /api/patrons/{id}: Updates an existing patron by ID (Provide JSON body).
DELETE /api/patrons/{id}: Removes a patron by ID.
Borrowing Records
POST /api/borrow/{bookId}/patron/{patronId}: Records a patron borrowing a book.
PUT /api/return/{bookId}/patron/{patronId}: Records the return of a borrowed book.
Database Configuration
Update src/main/resources/application.properties with your database configurations.

Validation and Error Handling
The API includes validation for requests and returns appropriate error messages and HTTP status codes.

Transaction Management
The application uses @Transactional annotation for managing transactions in service classes.

Testing
Run unit tests with:
mvn test
