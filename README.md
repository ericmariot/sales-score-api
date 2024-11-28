# Sales Score API

This project is a backend application built in Java and PostgreSQL for managing sellers, products and sales. It allows the registration, update and deletion of sellers, products and sales.
Additionally, it provides endpoints for the top sellers and most popular items.

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [API Collection](#api-collection)
- [Optionals](#optionals)
    - [Authentication and Authorization](#authentication-and-authorization)

## Prerequisites

Before running the system, make sure you have the following installed on your machine:
- Java 11 or later
- Maven
- Docker

## Running the Application

1. Clone and Move to the project:
```bash
git clone git@github.com:ericmariot/sales-score-api.git
cd sales-score-api
```

<br>

2. Build the project:
```bash
mvn clean install
```

<br>

3. Create a `.env` file based on the `.env.example` that can be found at the root of the project.

<br>

4. Run the database Docker image:
```bash
docker-compose up
```

<br>

5. Run the Application
```bash
mvn spring-boot:run
```

You can now check if the API is running at [localhost:8080/api/health](http://localhost:8080/api/health)

## Running tests

Tests can be ran with Maven as follow:
```bash
mvn test
```

## API Collection

A Postman JSON Collection can be found at the root of the project with the name [`sales-score-api.postman_collection.json`](https://github.com/ericmariot/sales-score-api/blob/main/sales-score-api.postman_collection.json).


## Database Diagram

[diagram](https://dbdiagram.io/d/65249618ffbf5169f05ab90f)

![image](https://github.com/user-attachments/assets/93e609d6-e37c-40af-a607-941f62a845f1)


## Optionals

### Authentication and Authorization

<b>How would you implement Authentication and Authorization for the API (which protocol, market-ready tool, etc.)?</b>

I would implement OAuth 2.0 protocol with JWT (JSON Web Tokens). This approach ensures a secure token-based authentication.

1. Authentication
    - Using a third-party authentication and authorization system like Auth0, Keycloak and such.
<br>
2. Authorization
    - The backend validates the JWT included in the `Authorization` header of each API request (Bearer<token>);
    - The token is verified using the public key provided by the third-party software (Auth0);
    - Can implement role-based access control (e.g `roles`, `permissions`, `userId`).
<br>
3. API Flow
    - The client sends the access token as a `Bearer` token in the `Authorization` header;
    - The backend security layer verifies the token's signature and expiration;
    - If the token is valid:
        - Extract pertinent information such as `roles` or `permissions` from the token payload;
        - Grant or deny access;
    - If the token is invalid the API responds with `401 Unauthorized` or `403 Forbidden`.
<br>
4. Additional considerations
    - Token expiration and refresh;
    - API security with HTTPS and CORS;
    - Auditing and Monitoring (Grafana, Prometheus)