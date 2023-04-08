# Library Management

This is a Spring Boot project that provides a RESTful API for managing a library's book inventory using Apache Tomcat 9, Java 11, and MongoDB.

## Installation

To install and run the Library Management project, follow these steps:

- Clone the repository to your local machine using <b>git clone https://github.com/chand1135/library-management.git</b>
- Navigate to the project directory using <b>cd library-management</b>
- Build the project using <b>mvn clean install</b>
- Start the server using <b>mvn spring-boot:run</b>
- Open a web browser and navigate to <b>http://localhost:8080/api/v1</b> to view the application

## Usage

The Library Management project provides the following endpoints:

### Users
- GET /user: Retrieves a list of all users in the system.
- GET /user/{id}: Retrieves a specific user from the system by ID.
- POST /user: Adds a new user to the system.
- PUT /user/{id}: Updates an existing user in the system by ID.
- DELETE /user/{id}: Deletes a specific user from the system by ID.

### Books
- GET /book: Retrieves a list of all books in the library's inventory.
- GET /book/{id}: Retrieves a specific book from the library's inventory by ID.
- POST /book: Adds a new book to the library's inventory.
- PUT /book/{id}: Updates an existing book in the library's inventory by ID.
- DELETE /book/{id}: Deletes a specific book from the library's inventory by ID.

### Authors
- GET /author: Retrieves a list of all authors in the system.
- GET /author/{id}: Retrieves a specific author from the system by ID.
- POST /author: Adds a new author to the system.
- PUT /author/{id}: Updates an existing author in the system by ID.
- DELETE /author/{id}: Deletes a specific author from the system by ID.

### Genres
- GET /genre: Retrieves a list of all genres in the system.
- GET /genre/{id}: Retrieves a specific genre from the system by ID.
- POST /genre: Adds a new genre to the system.
- PUT /genre/{id}: Updates an existing genre in the system by ID.
- DELETE /genre/{id}: Deletes a specific genre from the system by ID.

### Publishers
- GET /publisher: Retrieves a list of all publishers in the system.
- GET /publisher/{id}: Retrieves a specific publisher from the system by ID.
- POST /publisher: Adds a new publisher to the system.
- PUT /publisher/{id}: Updates an existing publisher in the system by ID.
- DELETE /publisher/{id}: Deletes a specific publisher from the system by ID.

## Contributing

We welcome contributions to the Library Management project! To contribute:

- Fork the repository to your own GitHub account.
- Clone the repository to your local machine using <b>git clone https://github.com/chand1135/library-management.git</b>
- Create a new branch for your changes using <b>git checkout -b feature/your-feature-name</b>
- Make your changes and commit them using <b>git commit -m "Your commit message"</b>
- Push your changes to your forked repository

## Acknowledgements

- Spring Boot 2.7.6
- Apache Tomcat 9
- Java 11 or higher
- MongoDB
