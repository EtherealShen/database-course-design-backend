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
    quantity INT NOT NULL DEFAULT 0,
    total_price DECIMAL(10, 2) NOT NULL DEFAULT 0,
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
    total_price DECIMAL(10, 2) NOT NULL DEFAULT 0,
    purchase_id INT NOT NULL,
    product_id INT NOT NULL,
    remarks TEXT NULL DEFAULT NULL,
    is_del ENUM('0','1') DEFAULT '1',
    INDEX purchase_id (purchase_id ASC),
    FOREIGN KEY (purchase_id) REFERENCES purchase (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);


create definer = root@localhost view details_view as
select `Supermarket`.`detail`.`id`         AS `detail_id`,
       `Supermarket`.`purchase`.`id`         AS `purchase_id`,
       `Supermarket`.`product`.`id`         AS `product_id`,
       `Supermarket`.`detail`.`quantity`          AS `quantity`,
       `Supermarket`.`product`.`unit_price` AS `unit_price`,
       `Supermarket`.`detail`.`total_price`      AS `total_price`,
       `Supermarket`.`detail`.`remarks`     AS `remarks`
from `Supermarket`.`detail`
         join `Supermarket`.`product`
         join `Supermarket`.purchase
where ( (`Supermarket`.`detail`.`purchase_id` = `Supermarket`.`purchase`.`id`) AND
    (`Supermarket`.`detail`.`product_id` = `Supermarket`.`product`.`id`));
