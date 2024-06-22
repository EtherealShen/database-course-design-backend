CREATE TRIGGER update_detail_total_price
    BEFORE UPDATE ON detail
    FOR EACH ROW
BEGIN
    DECLARE product_quantity INT;
    DECLARE product_unit_price DECIMAL(10, 2);
    DECLARE calculated_total_price DECIMAL(10, 2);

    -- 获取产品的quantity和unit_price
    SELECT quantity, unit_price INTO product_quantity, product_unit_price
    FROM product
    WHERE id = NEW.product_id;

    -- 计算total_price
    SET calculated_total_price = product_quantity * product_unit_price;

    -- 更新detail表中的total_price
    SET NEW.total_price = calculated_total_price;
END//

DELIMITER ;