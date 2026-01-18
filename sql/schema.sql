-- USERS
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
        CHECK (role IN ('ADMIN', 'KASIR'))
);

-- PRODUCT
CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DOUBLE PRECISION NOT NULL CHECK (price > 0),
    stock INTEGER NOT NULL CHECK (stock >= 0)
);

-- SALES TRANSACTION
CREATE TABLE sales_transaction (
    transaction_id SERIAL PRIMARY KEY,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cashier VARCHAR(50) NOT NULL,
    total DOUBLE PRECISION NOT NULL CHECK (total >= 0)
);

-- TRANSACTION DETAIL
CREATE TABLE transaction_detail (
    detail_id SERIAL PRIMARY KEY,
    transaction_id INTEGER NOT NULL,
    product_code VARCHAR(20) NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    subtotal DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_transaction
        FOREIGN KEY (transaction_id)
        REFERENCES sales_transaction(transaction_id)
        ON DELETE CASCADE
);

-- SEED DATA
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN'),
('kasir', 'kasir123', 'KASIR');

INSERT INTO product (code, name, category, price, stock) VALUES
('P001', 'Beras Premium', 'Pangan', 12000, 50),
('P002', 'Gula Pasir', 'Pangan', 14000, 40),
('P003', 'Minyak Goreng', 'Pangan', 16000, 30);
