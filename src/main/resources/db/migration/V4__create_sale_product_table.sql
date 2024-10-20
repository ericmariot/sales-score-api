CREATE TABLE
    sale_product (
        id SERIAL PRIMARY KEY,
        price NUMERIC(10, 2) NOT NULL,
        quantity INT NOT NULL,
        sale_id INT NOT NULL,
        product_id INT NOT NULL,
        total DECIMAL(10, 2) GENERATED ALWAYS AS (quantity * price) STORED,
        CONSTRAINT fk_sale FOREIGN KEY (sale_id) REFERENCES sale(id),
        CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
    );