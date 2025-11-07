-- Database schema for Banking System
CREATE TABLE IF NOT EXISTS customers (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    contact VARCHAR(50),
    customer_type VARCHAR(20) NOT NULL, -- 'INDIVIDUAL' or 'COMPANY'
    dob VARCHAR(20), -- For individuals
    national_id VARCHAR(20), -- For individuals
    next_of_kin VARCHAR(100), -- For individuals
    company_number VARCHAR(20), -- For companies
    date_of_incorporation VARCHAR(20) -- For companies
);

CREATE TABLE IF NOT EXISTS accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    customer_id VARCHAR(20) NOT NULL,
    account_type VARCHAR(20) NOT NULL, -- 'SAVINGS', 'INVESTMENT', 'CHEQUE'
    balance DECIMAL(15,2) DEFAULT 0.00,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id VARCHAR(30) PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL, -- 'DEPOSIT', 'WITHDRAWAL', 'INTEREST'
    amount DECIMAL(15,2) NOT NULL,
    balance_after DECIMAL(15,2) NOT NULL,
    description VARCHAR(200),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

-- Insert sample data
INSERT INTO customers (id, name, address, contact, customer_type, dob, national_id, next_of_kin) 
VALUES ('CUST001', 'John Smith', 'Gaborone, Botswana', '+2671234567', 'INDIVIDUAL', '1990-05-15', '199015567890', 'Mary Smith');

INSERT INTO customers (id, name, address, contact, customer_type, company_number, date_of_incorporation) 
VALUES ('CUST002', 'ABC Enterprises', 'Francistown, Botswana', '+2677654321', 'COMPANY', 'CO123456', '2020-01-10');

INSERT INTO accounts (account_number, customer_id, account_type, balance) 
VALUES ('SAV001', 'CUST001', 'SAVINGS', 1500.75);

INSERT INTO accounts (account_number, customer_id, account_type, balance) 
VALUES ('INV001', 'CUST001', 'INVESTMENT', 5000.00);

INSERT INTO accounts (account_number, customer_id, account_type, balance) 
VALUES ('CHQ001', 'CUST002', 'CHEQUE', 25000.50);

INSERT INTO transactions (transaction_id, account_number, transaction_type, amount, balance_after, description) 
VALUES ('TXN001', 'SAV001', 'DEPOSIT', 1000.00, 1000.00, 'Initial deposit');

INSERT INTO transactions (transaction_id, account_number, transaction_type, amount, balance_after, description) 
VALUES ('TXN002', 'SAV001', 'DEPOSIT', 500.75, 1500.75, 'Additional deposit');