USE librarydb;

CREATE DATABASE librarydb;
USE librarydb;

-- Authors table
CREATE TABLE authors (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         birth_date DATE,
                         nationality VARCHAR(100)
) ENGINE=InnoDB;

-- Books table
CREATE TABLE books (
                       id BIGINT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       isbn VARCHAR(13) UNIQUE,
                       author_id BIGINT,
                       published_date DATE,
                       available_copies INT DEFAULT 0,
                       total_copies INT,
                       CONSTRAINT fk_books_authors FOREIGN KEY (author_id)
                           REFERENCES authors(id)
                           ON DELETE SET NULL
) ENGINE=InnoDB;

-- Loans table
CREATE TABLE loans (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       book_id BIGINT,
                       member_id BIGINT,
                       loan_date DATE DEFAULT (CURRENT_DATE),
                       return_date DATE,
                       status ENUM('BORROWED', 'RETURNED', 'OVERDUE') DEFAULT 'BORROWED',
                       CONSTRAINT fk_loans_books FOREIGN KEY (book_id)
                           REFERENCES books(id)
                           ON DELETE CASCADE,
                       CONSTRAINT fk_loans_members FOREIGN KEY (member_id)
                           REFERENCES members(id)
                           ON DELETE CASCADE
) ENGINE=InnoDB;


CREATE TABLE members (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL ,
                         joined_date DATE DEFAULT (CURRENT_DATE),
                         active BOOLEAN DEFAULT TRUE,
                         role ENUM('ADMIN' , 'CUSTOMER') DEFAULT  'CUSTOMER'
) ENGINE=InnoDB;


INSERT INTO members(id, name, email, password, role)
VALUES (1111111,'Admin User' , 'admin@text.com' , '1234', 'ADMIN' );

INSERT INTO members (id, name, email, password, role)
VALUES (3333333, 'Dummy User', 'dummy@example.com', '1234', 0);

DELETE FROM members
WHERE id IN (3333333, 4866546);



DROP table members;
#
DROP TABLE loans;
DROP TABLE books;
DROP TABLE authors;


SELECT * FROM authors;

SELECT * FROM members;




DESCRIBE members;
describe books;

TRUNCATE TABLE member;

show tables;

select * from loans;

INSERT INTO loans (id, book_id, member_id, loan_date, return_date, status)
VALUES (1248009, 9880680, 7444114, '2025-08-25', NULL, 'BORROWED');


INSERT INTO loans (id, book_id, member_id, loan_date, return_date, status) VALUES
(1248593, 2596550, 8629972, '2025-09-25', NULL, 'BORROWED'),
(6374209, 7930992, 8629972, '2025-09-15', '2025-09-25', 'RETURNED'),
(4928715, 9901898, 8629972, '2025-10-01', NULL, 'BORROWED'),

(1836240, 3402428, 5353509, '2025-09-18', NULL, 'OVERDUE'),
(7214982, 6087416, 5353509, '2025-09-10', '2025-09-20', 'RETURNED'),
(5682397, 3284128, 5353509, '2025-10-05', NULL, 'BORROWED'),

(2956043, 9880680, 5514533, '2025-09-28', NULL, 'BORROWED'),
(8371594, 4814376, 5514533, '2025-09-15', '2025-09-23', 'RETURNED'),
(4082361, 6294617, 5514533, '2025-09-05', NULL, 'OVERDUE'),

(9147052, 4201035, 7444114, '2025-10-02', NULL, 'BORROWED'),
(2064981, 7580863, 7444114, '2025-09-20', '2025-09-28', 'RETURNED'),
(5367405, 9578087, 7444114, '2025-09-12', NULL, 'OVERDUE'),

(8471253, 6315802, 9812711, '2025-09-25', NULL, 'BORROWED'),
(6920417, 5021215, 9812711, '2025-09-10', '2025-09-18', 'RETURNED'),
(3149586, 6087416, 9812711, '2025-09-05', NULL, 'OVERDUE'),

(9056132, 7930992, 1111111, '2025-09-30', NULL, 'BORROWED'),
(1275849, 9880680, 1111111, '2025-09-15', '2025-09-25', 'RETURNED'),
(4589630, 4814376, 1111111, '2025-10-03', NULL, 'BORROWED');

select * from loans;

SELECT * FROM loans WHERE member_id='8629972';

truncate table loans;

SHOW TABLES;




mysqldump -u root -p library_db > ~/Downloads/LibraryDb/library_backup.sql


