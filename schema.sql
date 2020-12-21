DROP SCHEMA IF EXISTS films_db;
CREATE SCHEMA films_db;
USE films_db;

CREATE TABLE IF NOT EXISTS user_detail(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR (100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR (100) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS role(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    role_key VARCHAR(100) NOT NULL,
    role_description VARCHAR (255) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    username VARCHAR (100) NOT NULL,
    password VARCHAR (100) NOT NULL,
    user_detail_id INT(11) UNSIGNED NOT NULL,
    role_id INT(11) UNSIGNED NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_detail_id) REFERENCES user_detail(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS movie(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR (255) NOT NULL,
    stock INT(11) NOT NULL,
    rent_price INT(11) NOT NULL,
    sale_price INT(11) NOT NULL,
    availability BOOLEAN NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rent(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    rent_date DATETIME NOT NULL,
    return_date DATETIME NOT NULL,
    user_id INT(11) UNSIGNED NOT NULL,
    movie_id INT(11) UNSIGNED NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE IF NOT EXISTS sale(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    sale_date DATETIME NOT NULL,
    user_id INT(11) UNSIGNED NOT NULL,
    movie_id INT(11) UNSIGNED NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE IF NOT EXISTS movie_like(
    id INT(11) UNSIGNED AUTO_INCREMENT NOT NULL,
    status BOOLEAN NOT NULL,
    user_id INT(11) UNSIGNED NOT NULL,
    movie_id INT(11) UNSIGNED NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (movie_id) REFERENCES movie(id)
);

INSERT INTO role VALUE (NULL, 'ADMIN', 'administrator', NOW(), NOW());
INSERT INTO role VALUE (NULL, 'CUSTOMER', 'customer', NOW(), NOW());

INSERT INTO user_detail VALUE (NULL, 'admin', 'admin', 'admin address', 'admin@admin.com', NOW(), NOW());

INSERT INTO user (username, password, user_detail_id, role_id, created_date, modified_date)
SELECT 'admin', '$2a$10$EhSQctNSOGRPoJr/qjb4xe9KGHUL.gwtp4RjMTUwu3FMCRrLUUrDa' , d.id, (SELECT r.id FROM role r WHERE r.role_key = 'ADMIN'), NOW(), NOW()
FROM user_detail d
WHERE d.email = 'admin@admin.com';
