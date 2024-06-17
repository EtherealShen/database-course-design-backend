-- 如果Supermarket库存在，删除掉
DROP DATABASE IF EXISTS Supermarket;

-- 创建Supermarket库
CREATE DATABASE Supermarket;

-- 进入Supermarket库
USE Supermarket;

-- 创建客户表customer
CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(255),
    customer_abbreviation VARCHAR(50),
    address VARCHAR(255),
    company_phone VARCHAR(20),
    email VARCHAR(255),
    contact_person VARCHAR(100),
    contact_phone VARCHAR(20),
    remarks TEXT
);

-- 创建产品表product
CREATE TABLE product (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(255),
    unit_price DECIMAL(10, 2),
    customer_id INT,
    product_description TEXT,
    remarks TEXT,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

-- 创建员工表employee
CREATE TABLE employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(255),
    employee_password VARCHAR(255),
    employee_level ENUM('0', '1'),
    employee_phone VARCHAR(20),
    employee_salary DECIMAL(10, 2),
    remarks TEXT
);

-- 创建采购表purchase
CREATE TABLE purchase (
    purchase_id INT PRIMARY KEY,
    employee_id INT,
    purchase_quantity INT,
    purchase_total_price DECIMAL(10, 2),
    purchase_time DATETIME,
    remarks TEXT,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

-- 创建用户表User
CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_account VARCHAR(255),
    user_password VARCHAR(255),
    user_level ENUM('0','1') DEFAULT '0'
);