CREATE TABLE
    salesperson (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        registration VARCHAR(255) UNIQUE NOT NULL
    )