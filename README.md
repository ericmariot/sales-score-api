# Sales Score API

This project is a backend application built in Java and PostgreSQL for managing sellers, products and sales. It allows the registration, update and deletion of sellers, products and sales.
Additionally, it provides endpoints for the top sellers and most popular items.

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [API Collection](#api-collection)

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

## Running tests

Tests can be ran with Maven as follow:
```bash
mvn test
```

## API Collection

A Postman JSON Collection can be found at the root of the project with the name `sales-score-api.postman_collection.json`.


## Database Diagram

https://dbdiagram.io/d/65249618ffbf5169f05ab90f

![image](https://github.com/user-attachments/assets/93e609d6-e37c-40af-a607-941f62a845f1)
