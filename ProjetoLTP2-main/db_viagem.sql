create database db_viagem;
use db_viagem;

CREATE TABLE clientes (
    cliente_id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    tipo_cliente ENUM('nacional', 'estrangeiro') NOT NULL,
    cpf VARCHAR(14) UNIQUE, -- para clientes nacionais
    passaporte VARCHAR(20) UNIQUE, -- para clientes estrangeiros
    telefone VARCHAR(15),
    email VARCHAR(255)
);
CREATE TABLE pacotes (
    pacote_id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    destino VARCHAR(255) NOT NULL,
    duracao_dias INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    tipo_pacote ENUM('aventura', 'luxo', 'cultural') NOT NULL
);
CREATE TABLE servicos (
    servico_id INT PRIMARY KEY AUTO_INCREMENT,
    nome_servico VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL
);
CREATE TABLE clientes_pacotes (
    cliente_id INT,
    pacote_id INT,
    PRIMARY KEY (cliente_id, pacote_id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
    FOREIGN KEY (pacote_id) REFERENCES pacotes(pacote_id)
);
CREATE TABLE pedidos_servicos (
    pedido_id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT,
    pacote_id INT,
    servico_id INT,
    data_pedido DATE,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
    FOREIGN KEY (pacote_id) REFERENCES pacotes(pacote_id),
    FOREIGN KEY (servico_id) REFERENCES servicos(servico_id)
);
