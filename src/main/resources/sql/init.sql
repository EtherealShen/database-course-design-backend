DROP DATABASE IF EXISTS Supermarket;

CREATE DATABASE Supermarket;

USE Supermarket;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 创建用户表User
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL ,
    level ENUM('0','1') DEFAULT '1',
    is_del ENUM('0','1') DEFAULT '1',
    UNIQUE INDEX account (account ASC)
);


-- 创建员工表employee
CREATE TABLE employee (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    level int NOT NULL,
    tel VARCHAR(20) NOT NULL,
    salary decimal(10, 2) NULL DEFAULT NULL,
    remarks TEXT NULL DEFAULT NULL,
    is_del ENUM('0','1') DEFAULT '1'
);

-- 创建供应商表producer
CREATE TABLE producer (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    abbreviation VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    tel VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    contact_person VARCHAR(100) NOT NULL,
    contact_tel VARCHAR(20) NOT NULL,
    remarks TEXT NULL DEFAULT NULL,
    is_del ENUM('0','1') DEFAULT '1'
);

-- 创建产品表product
CREATE TABLE product (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    description TEXT NULL DEFAULT NULL,
    remarks TEXT NULL DEFAULT NULL,
    producer_id INT NOT NULL,
    is_del ENUM('0','1') DEFAULT '1',
    INDEX customer_id (producer_id ASC),
    FOREIGN KEY (producer_id) REFERENCES producer (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- 创建采购表purchase
CREATE TABLE purchase (
    id INT PRIMARY KEY NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    time DATETIME NOT NULL ,
    remarks TEXT NULL DEFAULT NULL,
    employee_id INT NOT NULL,
    is_del ENUM('0','1') DEFAULT '1',
    INDEX employee_id (employee_id ASC),
    FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

-- 创建明细表detail
CREATE TABLE detail (
    id INT PRIMARY KEY NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    purchase_id INT NOT NULL,
    product_id INT NOT NULL,
    remarks TEXT NULL DEFAULT NULL,
    is_del ENUM('0','1') DEFAULT '1',
    INDEX purchase_id (purchase_id ASC),
    FOREIGN KEY (purchase_id) REFERENCES purchase (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);