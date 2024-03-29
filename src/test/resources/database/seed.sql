DROP TABLE transactions;

CREATE TABLE transactions (
   id BIGINT NOT NULL,
   type VARCHAR(50) NOT NULL,
   iban VARCHAR(25) NOT NULL,
   cnp VARCHAR(25) NOT NULL,
   name VARCHAR(50) NOT NULL,
   description VARCHAR(100) NOT NULL,
   sum DECIMAL NOT NULL,
);

INSERT INTO transactions VALUES (1, 'IBAN_TO_IBAN', 'RO09BCYP0000001234567890', '1901223211419', 'Marius Popa', 'car payment', 2500);
INSERT INTO transactions VALUES (2, 'WALLET_TO_IBAN', 'RO09BCYP0000001234567891', '2901223211419', 'Gabriela Popa', 'dog grooming', 2500);
INSERT INTO transactions VALUES (3, 'IBAN_TO_IBAN', 'RO09BCYP0000001234567890', '1901223211419', 'Marius Popa', 'groceries', 200);
INSERT INTO transactions VALUES (4, 'IBAN_TO_IBAN', 'RO09BCYP0000001234567890', '1901223211419', 'Marius Popa', 'car washing', 100);
INSERT INTO transactions VALUES (4, 'WALLET_TO_IBAN', 'RO09BCYP0000001234567892', '1901223211419', 'Marius Popa', 'car repairs', 5000);