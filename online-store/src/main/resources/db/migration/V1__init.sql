CREATE TABLE categories
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    title      varchar(255) NOT NULL,
    created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='таблица категорий продуктов';

CREATE TABLE products
(
    id          bigint        NOT NULL AUTO_INCREMENT,
    title       varchar(255)  NOT NULL,
    price       decimal(8, 2) NOT NULL,
    category_id bigint        NOT NULL,
    created_at  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY           fk_product_category_idx (category_id),
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='таблица продуктов';

INSERT INTO categories (title) values
    ('продукты питания и напитки'),
    ('техника и электроника'),
    ('товары для детей');

INSERT INTO products (title, price, category_id)
values ('хлеб', 44.5, 1),
       ('молоко', 82.32, 1),
       ('масло сливочное', 200.87, 1),
       ('сыр российский', 450.32, 1),
       ('наушники', 2500.12, 2),
       ('смартфон', 12000, 2),
       ('мишка плюшевый', 2000.12, 3),
       ('паровоз', 500.32, 3);
