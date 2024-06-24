USE supermarket;

DROP TRIGGER IF EXISTS insert_detail;
DROP TRIGGER IF EXISTS update_detail;


CREATE TRIGGER insert_detail
    BEFORE insert on detail
    FOR EACH ROW
BEGIN

    DECLARE product_unit_price DECIMAL(10, 2);
    DECLARE calculated_total_price DECIMAL(10, 2);

    SELECT unit_price INTO product_unit_price
    FROM product
    WHERE id = NEW.product_id;

    SET calculated_total_price = NEW.quantity * product_unit_price;
    SET NEW.total_price = calculated_total_price;

    UPDATE purchase
    SET total_price = total_price + calculated_total_price
    WHERE id = NEW.purchase_id;

    UPDATE purchase
    SET quantity = quantity + NEW.quantity
    WHERE id = NEW.purchase_id;
END;


CREATE TRIGGER update_detail
    BEFORE update on detail
    FOR EACH ROW
BEGIN

    DECLARE pre_total_price DECIMAL(10, 2);
    DECLARE now_total_price DECIMAL(10, 2);
    DECLARE pre_unit_price DECIMAL(10, 2);
    DECLARE now_unit_price DECIMAL(10, 2);

    SELECT unit_price INTO pre_unit_price
    FROM product
    WHERE id = OLD.product_id;

    SELECT unit_price INTO now_unit_price
    FROM product
    WHERE id = NEW.product_id;


    SET now_total_price = NEW.quantity * now_unit_price;
    SET pre_total_price = OLD.quantity * pre_unit_price;

    SET NEW.total_price = now_total_price;

    UPDATE purchase
    SET total_price =  total_price - pre_total_price + now_total_price
    WHERE id = NEW.purchase_id;

    UPDATE purchase
    SET quantity = quantity - OLD.quantity + NEW.quantity
    WHERE id = NEW.purchase_id;
END;



