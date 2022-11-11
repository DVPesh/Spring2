CREATE TABLE IF NOT EXISTS orders
(
    id         bigint        NOT NULL AUTO_INCREMENT,
    visitor_id bigint        NOT NULL,
    total_cost decimal(8, 2) NOT NULL,
    address    varchar(255)       DEFAULT NULL,
    phone      varchar(50)        DEFAULT NULL,
    created_at timestamp     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp     NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY fk_visitor_order_idx (visitor_id),
    CONSTRAINT fk_visitor_order FOREIGN KEY (visitor_id) REFERENCES visitors (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='таблица заказов';

CREATE TABLE IF NOT EXISTS order_items
(
    id                bigint        NOT NULL AUTO_INCREMENT,
    product_id        bigint        NOT NULL,
    order_id          bigint        NOT NULL,
    quantity          int           NOT NULL,
    price_per_product decimal(8, 2) NOT NULL,
    cost              decimal(8, 2) NOT NULL,
    created_at        timestamp     NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        timestamp     NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY fk_products_idx (product_id),
    KEY fk_orders_idx (order_id),
    CONSTRAINT fk_orders FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_products FOREIGN KEY (product_id) REFERENCES products (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='таблица содержимого заказов';
