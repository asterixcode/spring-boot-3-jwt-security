# Spring Boot 3 JWT Security 

## About

JWT authentication and authorization implementation in a Spring Boot 3.2.3 application using Spring Security and PostgreSQL database to store user credentials.

Implementation is based on `Amigoscode` and `Bouali Ali` YouTube tutorial [Spring Boot 3 + Spring Security 6 - JWT Authentication and Authorisation](https://www.youtube.com/watch?v=KxqlJblhzfI) but using Java 21, Spring Boot 3.2.3, and newer version of JJWT library. 

Find more about `Bouali Ali` at his [YouTube channel](https://www.youtube.com/@BoualiAli)

### Features

- User registration and authentication with JWT
- Demo authenticated endpoint
- Password encryption with BCrypt
- Role-based authorization with Spring Security

### Technologies

- Java 21
- Spring Boot 3.2.3
- Spring Security 6.2.2
- JSON Web Token (JWT) with [jjwt library](https://github.com/jwtk/jjwt)
- BCrypt
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Getting Started

### Prerequisites

- Java 21
- Maven 3+
- PostgreSQL

### Build and Run

1. Clone the repository.
2. Create a PostgreSQL database named `jwt_security`.
3. Update the `application.yml` file with your database credentials.
4. Build the project using Maven:

```bash
mvn clean install
```

5. Run the project:

```bash
mvn spring-boot:run
```

6. The application will be running at `http://localhost:8080`.

### Usage

#### HTTP Client CLI

Find the `.http` file in the `http` directory and use it to test the API.

#### Postman

Import the Postman collection from the `http` directory.
