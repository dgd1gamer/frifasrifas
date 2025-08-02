-- ============================================
-- FLYWAY MIGRATION: V1__init.sql
-- Criação das tabelas principais do sistema
-- ============================================

-- TABELA: role
CREATE TABLE role (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- TABELA: user
CREATE TABLE "user" (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- TABELA PIVÔ: user_roles
CREATE TABLE user_roles (
    user_id UUID NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- TABELA: raffle
CREATE TABLE raffle (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    point_value DOUBLE PRECISION NOT NULL,
    point_quantity INTEGER NOT NULL,
    prize_quantity INTEGER NOT NULL,
    image_url VARCHAR(500),
    payment_link TEXT NOT NULL,
    contact_phone VARCHAR(50) NOT NULL,
    end_date TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    owner_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES "user"(id) ON DELETE CASCADE
);

-- TABELA: ticket
CREATE TABLE tickets (
    id UUID PRIMARY KEY,
    number INTEGER NOT NULL,
    buyer_name VARCHAR(255),
    buyer_phone VARCHAR(50),
    status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    raffle_id UUID NOT NULL,
    FOREIGN KEY (raffle_id) REFERENCES raffle(id) ON DELETE CASCADE
);

-- TABELA: sorteio
CREATE TABLE sorteio (
    id UUID PRIMARY KEY,
    raffle_id UUID UNIQUE NOT NULL,
    execution_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (raffle_id) REFERENCES raffle(id) ON DELETE CASCADE
);

-- TABELA: sorteio_numbers (valores sorteados)
CREATE TABLE sorteio_numbers (
    sorteio_id UUID NOT NULL,
    number INTEGER NOT NULL,
    FOREIGN KEY (sorteio_id) REFERENCES sorteio(id) ON DELETE CASCADE
);
