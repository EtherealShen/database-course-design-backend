create table customer
(
    customer_id           int          not null
        primary key,
    customer_name         varchar(255) null,
    customer_abbreviation varchar(50)  null,
    address               varchar(255) null,
    company_phone         varchar(20)  null,
    email                 varchar(255) null,
    contact_person        varchar(100) null,
    contact_phone         varchar(20)  null,
    remarks               text         null
);

create table employee
(
    employee_id       int             not null
        primary key,
    employee_name     varchar(255)    null,
    employee_password varchar(255)    null,
    employee_level    enum ('0', '1') null,
    employee_phone    varchar(20)     null,
    employee_salary   decimal(10, 2)  null,
    remarks           text            null
);

create table product
(
    product_id          int            not null
        primary key,
    product_name        varchar(255)   null,
    unit_price          decimal(10, 2) null,
    customer_id         int            null,
    product_description text           null,
    remarks             text           null,
    constraint product_ibfk_1
        foreign key (customer_id) references customer (customer_id)
);

create index customer_id
    on product (customer_id);

create table purchase
(
    purchase_id          int            not null
        primary key,
    employee_id          int            null,
    purchase_quantity    int            null,
    purchase_total_price decimal(10, 2) null,
    purchase_time        datetime       null,
    remarks              text           null,
    constraint purchase_ibfk_1
        foreign key (employee_id) references employee (employee_id)
);

create index employee_id
    on purchase (employee_id);

create table user
(
    user_id       int auto_increment
        primary key,
    user_account  varchar(255)                null,
    user_password varchar(255)                null,
    user_level    enum ('0', '1') default '0' null
);
