CREATE TABLE IF NOT EXISTS visitors
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    username   varchar(100) NOT NULL,
    password   varchar(255) NOT NULL,
    email      varchar(50)       DEFAULT NULL,
    created_at timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY username_UNIQUE (username),
    UNIQUE KEY password_UNIQUE (password),
    UNIQUE KEY email_UNIQUE (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='таблица пользователей';

CREATE TABLE IF NOT EXISTS roles
(
    id         bigint      NOT NULL AUTO_INCREMENT,
    name       varchar(50) NOT NULL,
    created_at timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY name_UNIQUE (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='таблица ролей';

CREATE TABLE IF NOT EXISTS visitors_roles
(
    visitor_id bigint    NOT NULL,
    role_id    bigint    NOT NULL,
    created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (visitor_id, role_id),
    KEY fk_roles_idx (role_id),
    CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_visitors FOREIGN KEY (visitor_id) REFERENCES visitors (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='таблица ролей пользователей';

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into visitors (username, password, email)
values ('Нина', '$2a$12$wuyCR4n295tUNf82kVp1H.h66PxjsTZHEGLJfzlw4Qqf4fbJTQcpu', 'nina@mail.ru'),
       ('Олег', '$2a$12$oi8NTLJyxhDham6lhr7K8.iuP916Tc5ygLmZhWKlSh8u2Oobb0I/i', 'oleg@mail.ru'),
       ('Николай', '$2a$12$7Z1m9cW.Huh4sCiNAqWVOeoIVo55mLMhx8b9XqRiBUNnTnOb3eRk.', 'nick@mail.ru');

insert into visitors_roles (visitor_id, role_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (3, 1);
