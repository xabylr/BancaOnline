DROP DATABASE IF EXISTS  bancaonline;
DROP USER IF EXISTS 'bancaonline'@'localhost';
CREATE USER 'bancaonline'@'localhost' IDENTIFIED BY 'pendejo';
GRANT ALL PRIVILEGES ON bancaonline.* TO 'bancaonline'@'localhost';
CREATE DATABASE bancaonline;

USE bancaonline;

CREATE TABLE empleado (
    dni         INT PRIMARY KEY,
    password    VARCHAR(100),
    nombre      VARCHAR(30),
    apellidos   VARCHAR(100)
);



CREATE TABLE cuentacorriente (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    entidad         NUMERIC(4,0),
    oficina         NUMERIC(4,0),
    cc              NUMERIC(10,0),
    saldo           BIGINT,
    decimales       INT,
    divisa          VARCHAR(3),
    fechacreacion   BIGINT,
    CONSTRAINT UC_CC UNIQUE (entidad, oficina, cc)
);


CREATE TABLE cliente (
    dni         INT PRIMARY KEY,
    password    VARCHAR(100),
    nombre      VARCHAR(30),
    apellidos   VARCHAR(100),
    cuenta      INT,
    email       VARCHAR(100),
    domicilio   VARCHAR(100),
    telefono    NUMERIC(9,0),
    FOREIGN KEY (cuenta) REFERENCES cuentacorriente (id)
);


CREATE TABLE movimiento(
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    remitente               INT,
    receptor                INT,
    concepto                VARCHAR(200),
    fecha                   BIGINT,
    cuantia                 BIGINT,
    decimales               INT,
    divisa                  VARCHAR(3),
    saldoRttPrev            BIGINT,
    saldoRttPrevDec         INT,
    saldoRttPrevDiv         VARCHAR(3),
    saldoRcpPrev            BIGINT,
    saldoRcpPrevDec         INT,
    saldoRcpPrevDiv         VARCHAR(3),
    FOREIGN KEY (remitente) REFERENCES cuentacorriente (id),
    FOREIGN KEY (receptor)  REFERENCES cuentacorriente (id)
);
