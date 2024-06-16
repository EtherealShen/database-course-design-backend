

INSERT INTO customer (customer_id, customer_name, customer_abbreviation, address, company_phone, email, contact_person, contact_phone, remarks)
VALUES
    (1, 'ABC Suppliers', 'ABC', '123 Main Street', '123-456-7890', 'abc@supplier.com', 'John Doe', '987-654-3210', 'Supplier for electronics'),
    (2, 'XYZ Distributors', 'XYZ', '456 Elm Street', '456-789-0123', 'xyz@distributor.com', 'Jane Smith', '012-345-6789', 'Supplier for household items'),
    (3, '123 Wholesalers', '123', '789 Oak Avenue', '789-012-3456', '123@wholesaler.com', 'Mike Johnson', '345-678-9012', 'Supplier for clothing'),
    (4, 'Global Imports', 'Global', '987 Pine Road', '987-654-3210', 'global@imports.com', 'Sarah Thompson', '678-901-2345', 'Supplier for furniture'),
    (5, 'Best Deals', 'Best', '321 Cedar Lane', '012-345-6789', 'best@deals.com', 'David Miller', '901-234-5678', 'Supplier for appliances'),
    (6, 'Superior Goods', 'Superior', '654 Maple Avenue', '345-678-9012', 'superior@goods.com', 'Amy Wilson', '234-567-8901', 'Supplier for beauty products'),
    (7, 'Mega Suppliers', 'Mega', '890 Walnut Street', '789-012-3456', 'mega@suppliers.com', 'Michael Brown', '567-890-1234', 'Supplier for groceries'),
    (8, 'Prime Distributors', 'Prime', '432 Oak Avenue', '234-567-8901', 'prime@distributors.com', 'Jennifer Davis', '890-123-4567', 'Supplier for electronics'),
    (9, 'Global Imports', 'Global', '876 Pine Road', '901-234-5678', 'global@imports.com', 'Daniel Wilson', '456-789-0123', 'Supplier for furniture'),
    (10, 'Best Deals', 'Best', '123 Cedar Lane', '678-901-2345', 'best@deals.com', 'Emily Johnson', '012-345-6789', 'Supplier for appliances');

INSERT INTO product (product_id, product_name, unit_price, customer_id, product_description, remarks)
VALUES
    (1, 'Laptop', 999.99, 1, 'High-performance laptop with Intel processor', 'Gaming laptop'),
    (2, 'TV', 699.99, 2, '55-inch LED TV with 4K resolution', 'Smart TV'),
    (3, 'T-Shirt', 19.99, 3, 'Cotton T-shirt in various colors', 'Unisex T-shirt'),
    (4, 'Sofa', 799.99, 4, '3-seater fabric sofa with wooden frame', 'Modern design'),
    (5, 'Refrigerator', 899.99, 5, 'Energy-efficient refrigerator with double doors', 'Large capacity'),
    (6, 'Shampoo', 9.99, 6, 'Moisturizing shampoo for all hair types', 'Contains natural extracts'),
    (7, 'Cereal', 4.99, 7, 'Whole grain cereal with added vitamins', 'Healthy breakfast option'),
    (8, 'Headphones', 49.99, 8, 'Wireless headphones with noise cancellation', 'Bluetooth connectivity'),
    (9, 'Chair', 49.99, 4, 'Ergonomic office chair with adjustable features', 'Comfortable seating'),
    (10, 'Microwave', 79.99, 5, 'Compact microwave oven with multiple functions', 'Easy-to-use controls');


INSERT INTO employee (employee_id, employee_name, employee_password, employee_level, employee_phone, employee_salary, remarks)
VALUES
    (1, 'John Smith', 'password123', '0', '123-456-7890', 3000.00, 'Store manager'),
    (2, 'Jane Doe', 'secret456', '1', '234-567-8901', 2500.00, 'Sales representative'),
    (3, 'Mike Johnson', 'pass789', '1', '345-678-9012', 2500.00, 'Sales representative'),
    (4, 'Sarah Thompson', 'secure987', '1', '456-789-0123', 2500.00, 'Sales representative'),
    (5, 'David Miller', 'password321', '1', '567-890-1234', 2500.00, 'Sales representative'),
    (6, 'Amy Wilson', 'admin123', '2', '678-901-2345', 3500.00, 'Supervisor'),
    (7, 'Michael Brown', 'secret789', '2', '789-012-3456', 3500.00, 'Supervisor'),
    (8, 'Jennifer Davis', 'pass123', '2', '890-123-4567', 3500.00, 'Supervisor'),
    (9, 'Daniel Wilson', 'password789', '2', '901-234-5678', 3500.00, 'Supervisor'),
    (10, 'Emily Johnson', 'secure456', '2', '012-345-6789', 3500.00, 'Supervisor');

INSERT INTO purchase (purchase_id, employee_id, purchase_quantity, purchase_total_price, purchase_time, remarks)
VALUES
    (1, 1, 5, 249.95, '2024-06-16 10:00:00', 'Purchase of laptops'),
    (2, 2, 10, 699.90, '2024-06-16 11:30:00', 'Purchase of TVs'),
    (3, 3, 20, 399.80, '2024-06-16 13:45:00', 'Purchase of T-Shirts'),
    (4, 4, 3, 2399.97, '2024-06-16 14:30:00', 'Purchase of sofas'),
    (5, 5, 8, 799.92, '2024-06-16 15:15:00', 'Purchase of refrigerators'),
    (6, 6, 50, 499.50, '2024-06-16 16:00:00', 'Purchase of shampoos'),
    (7, 7, 100, 499.00, '2024-06-16 16:45:00', 'Purchase of cereals'),
    (8, 8, 15, 749.85, '2024-06-16 17:30:00', 'Purchase of headphones'),
    (9, 9, 4, 1999.96, '2024-06-16 18:15:00', 'Purchase of chairs'),
    (10, 10, 6, 479.94, '2024-06-16 19:00:00', 'Purchase of microwaves');

INSERT  INTO User (user_id, user_account, user_password)
VALUES
    (1,'123','123'),
    (2,'shen','123');