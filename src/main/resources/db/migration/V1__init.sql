-- ============================================
-- FLYWAY MIGRATION: V1__init.sql
-- Criação das tabelas principais do sistema
-- ============================================

-- V1__init.sql

CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE `user` (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id CHAR(36) NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES `user`(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE TABLE raffle (
    id CHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    point_value DOUBLE NOT NULL,
    point_quantity INT NOT NULL,
    prize_quantity INT NOT NULL,
    image_url VARCHAR(500),
    payment_link TEXT NOT NULL,
    contact_phone VARCHAR(50) NOT NULL,
    end_date DATETIME,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    owner_id CHAR(36) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES `user`(id) ON DELETE CASCADE
);

CREATE TABLE tickets (
    id CHAR(36) PRIMARY KEY,
    number INT NOT NULL,
    buyer_name VARCHAR(255),
    buyer_phone VARCHAR(50),
    status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    raffle_id CHAR(36) NOT NULL,
    FOREIGN KEY (raffle_id) REFERENCES raffle(id) ON DELETE CASCADE
);

CREATE TABLE sorteio (
    id CHAR(36) PRIMARY KEY,
    raffle_id CHAR(36) UNIQUE NOT NULL,
    execution_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (raffle_id) REFERENCES raffle(id) ON DELETE CASCADE
);

CREATE TABLE sorteio_numbers (
    sorteio_id CHAR(36) NOT NULL,
    number INT NOT NULL,
    FOREIGN KEY (sorteio_id) REFERENCES sorteio(id) ON DELETE CASCADE
);

