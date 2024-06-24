-- 插入供应商、产品和采购信息的存储过程

DELIMITER //

CREATE PROCEDURE InsertData(
    IN p_producer_name VARCHAR(255),
    IN p_producer_abbr VARCHAR(50),
    IN p_producer_address VARCHAR(255),
    IN p_producer_tel VARCHAR(20),
    IN p_producer_email VARCHAR(255),
    IN p_producer_contact_person VARCHAR(100),
    IN p_producer_contact_tel VARCHAR(20),
    IN p_producer_remarks TEXT,
    IN p_product_name VARCHAR(255),
    IN p_product_price DECIMAL(10, 2),
    IN p_product_desc TEXT,
    IN p_product_remarks TEXT,
    IN p_purchase_quantity INT,
    IN p_purchase_total_price DECIMAL(10, 2),
    IN p_purchase_time DATETIME,
    IN p_purchase_remarks TEXT,
    IN p_employee_id INT
)
BEGIN
    DECLARE producer_id INT;
    DECLARE product_id INT;

    -- 插入供应商数据
    INSERT INTO producer (name, abbreviation, address, tel, email, contact_person, contact_tel, remarks)
    VALUES (p_producer_name, p_producer_abbr, p_producer_address, p_producer_tel, p_producer_email, p_producer_contact_person, p_producer_contact_tel, p_producer_remarks);

    SET producer_id = LAST_INSERT_ID();

    -- 插入产品数据
    INSERT INTO product (name, unit_price, description, remarks, producer_id)
    VALUES (p_product_name, p_product_price, p_product_desc, p_product_remarks, producer_id);

    SET product_id = LAST_INSERT_ID();

    -- 插入采购数据
    INSERT INTO purchase (quantity, total_price, time, remarks, employee_id)
    VALUES (p_purchase_quantity, p_purchase_total_price, p_purchase_time, p_purchase_remarks, p_employee_id);

    -- 插入采购明细数据
    INSERT INTO detail (quantity, total_price, purchase_id, product_id, remarks)
    VALUES (p_purchase_quantity, p_purchase_total_price, LAST_INSERT_ID(), product_id, p_purchase_remarks);
END //

DELIMITER ;

-- 更新员工信息的存储过程

DELIMITER //

CREATE PROCEDURE UpdateEmployee(
    IN p_employee_id INT,
    IN p_employee_name VARCHAR(255),
    IN p_employee_password VARCHAR(255),
    IN p_employee_level INT,
    IN p_employee_tel VARCHAR(20),
    IN p_employee_salary DECIMAL(10, 2),
    IN p_employee_remarks TEXT,
    IN p_employee_is_del ENUM('0','1')
)
BEGIN
    UPDATE employee
    SET name = p_employee_name,
        password = p_employee_password,
        level = p_employee_level,
        tel = p_employee_tel,
        salary = p_employee_salary,
        remarks = p_employee_remarks,
        is_del = p_employee_is_del
    WHERE id = p_employee_id;
END //

DELIMITER ;

-- 删除产品和其相关的采购明细数据的存储过程

DELIMITER //

CREATE PROCEDURE DeleteProductAndDetails(
    IN p_product_id INT
)
BEGIN
    -- 删除相关的采购明细数据
    DELETE FROM detail WHERE product_id = p_product_id;

    -- 删除产品数据
    DELETE FROM product WHERE id = p_product_id;
END //

DELIMITER ;
