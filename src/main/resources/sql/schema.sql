CREATE SCHEMA IF NOT EXISTS mall;

USE mall;

CREATE TABLE product
(
    id          bigint AUTO_INCREMENT
        PRIMARY KEY,
    name        varchar(100)                       NOT NULL,
    price       decimal(10, 2)                     NOT NULL,
    stock       int      DEFAULT 0                 NOT NULL,
    description text                               NULL,
    image_url   varchar(255)                       NULL,
    create_time datetime DEFAULT CURRENT_TIMESTAMP NULL,
    status      tinyint(1)                         NOT NULL
);

CREATE TABLE user
(
    id          bigint AUTO_INCREMENT
        PRIMARY KEY,
    username    varchar(50)                        NOT NULL,
    password    varchar(100)                       NOT NULL,
    phone       varchar(20)                        NULL,
    create_time datetime DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT username
        UNIQUE (username)
);

CREATE TABLE address
(
    id             bigint AUTO_INCREMENT
        PRIMARY KEY,
    user_id        bigint                             NOT NULL,
    receiver_name  varchar(50)                        NOT NULL,
    receiver_phone varchar(20)                        NOT NULL,
    full_address   varchar(255)                       NOT NULL,
    create_time    datetime DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT fk_address_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON DELETE CASCADE
);

CREATE TABLE cart_item
(
    id          bigint AUTO_INCREMENT
        PRIMARY KEY,
    user_id     bigint                             NOT NULL,
    product_id  bigint                             NOT NULL,
    quantity    int      DEFAULT 1                 NOT NULL,
    create_time datetime DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT fk_cart_product
        FOREIGN KEY (product_id) REFERENCES product (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_cart_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON DELETE CASCADE
);

CREATE TABLE orders
(
    id          bigint AUTO_INCREMENT
        PRIMARY KEY,
    user_id     bigint                             NOT NULL,
    address_id  bigint                             NOT NULL,
    total_price decimal(10, 2)                     NOT NULL,
    status      tinyint  DEFAULT 0                 NOT NULL,
    create_time datetime DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT fk_order_address
        FOREIGN KEY (address_id) REFERENCES address (id),
    CONSTRAINT fk_order_user
        FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE order_item
(
    id         bigint AUTO_INCREMENT
        PRIMARY KEY,
    order_id   bigint         NOT NULL,
    product_id bigint         NOT NULL,
    price      decimal(10, 2) NOT NULL,
    quantity   int            NOT NULL,
    CONSTRAINT fk_orderitem_order
        FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_orderitem_product
        FOREIGN KEY (product_id) REFERENCES product (id)
);



