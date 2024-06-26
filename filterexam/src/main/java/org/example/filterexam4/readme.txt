1. 필요한 의존성 주입
2. application.yml 파일에 필요한 설정들을 추가.
3. 필요한 객체들을 하나씩 작성해 나가봅시다.


## 데이터베이스 설정!!

create database newDB;

grant all privileges on newDB.* to 'carami'@'%';

flush privileges ;

use newDB;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, name, email) VALUES
                                                        ('urstory', '1234', 'Urstory Name', 'urstory@example.com'),
                                                        ('carami', '1234', 'Carami Name', 'carami@example.com');


select * from users;

select * from user_roles;

