CREATE TABLE
    sale (
        id SERIAL PRIMARY KEY,
        total NUMERIC(10, 2) NOT NULL,
        sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        salesperson_id INT NOT NULL,
        CONSTRAINT fk_salesperson FOREIGN KEY (salesperson_id) REFERENCES salesperson(id)
    );